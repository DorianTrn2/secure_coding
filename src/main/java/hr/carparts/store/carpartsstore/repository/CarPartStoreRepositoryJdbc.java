package hr.carparts.store.carpartsstore.repository;

import hr.carparts.store.carpartsstore.model.CarPart;
import hr.carparts.store.carpartsstore.model.CarPartCategoryEnum;
import hr.carparts.store.carpartsstore.model.CarPartsSearchForm;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
@Primary
public class CarPartStoreRepositoryJdbc implements CarPartStoreRepository {
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    public CarPartStoreRepositoryJdbc(JdbcTemplate jdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource);
        simpleJdbcInsert.withTableName("CAR_PART")
                .usingGeneratedKeyColumns("ID");
    }

    @Override
    public List<CarPart> findAll() {
        return jdbcTemplate.query("SELECT * FROM CAR_PART", new CarPartRowMapper());
    }

    @Override
    public Optional<CarPart> findById(Integer id) {
        CarPart carPart = jdbcTemplate.queryForObject("SELECT * FROM CAR_PART WHERE ID = ?",
                new CarPartRowMapper(), id);

        if(Optional.ofNullable(carPart).isPresent()) {
            return Optional.of(carPart);
        }
        else {
            return Optional.empty();
        }
    }

    @Override
    public void save(CarPart newCarPart) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", newCarPart.getName());
        //params.addValue("category_id", newCarPart.getCategory().ordinal() - 1);
        params.addValue("price", newCarPart.getPrice());
        params.addValue("description", newCarPart.getDescription());

        simpleJdbcInsert.executeAndReturnKey(params);
    }

    @Override
    public List<CarPart> filterByCriteria(CarPartsSearchForm carPartsSearchForm) {
        StringBuilder sqlQuery = new StringBuilder("SELECT * FROM CAR_PART WHERE 1 = 1 ");
        List<String> queryArgs = new ArrayList<>();

        if(!carPartsSearchForm.getName().isEmpty()) {
            sqlQuery.append("AND NAME LIKE ('%'||?||'%') ");
            queryArgs.add(carPartsSearchForm.getName());
        }

        if(!carPartsSearchForm.getCategory().isEmpty()) {
            sqlQuery.append("AND CATEGORY_ID = ? ");
            queryArgs.add(String.valueOf(CarPartCategoryEnum.valueOf(
                    carPartsSearchForm.getCategory()).ordinal() + 1));
        }

        if(Optional.ofNullable(carPartsSearchForm.getLowerPrice()).isPresent()) {
            sqlQuery.append("AND PRICE >= ? ");
            queryArgs.add(carPartsSearchForm.getLowerPrice().toString());
        }

        if(Optional.ofNullable(carPartsSearchForm.getUpperPrice()).isPresent()) {
            sqlQuery.append("AND PRICE <= ? ");
            queryArgs.add(carPartsSearchForm.getUpperPrice().toString());
        }

        if(!carPartsSearchForm.getDescription().isEmpty()) {
            sqlQuery.append("AND DESCRIPTION LIKE ('%'||?||'%') ");
            queryArgs.add(carPartsSearchForm.getDescription());
        }

        Object[] preparedStatementArgs = new Object[queryArgs.size()];
        for(int i = 0; i < preparedStatementArgs.length; i++){
            preparedStatementArgs[i] = queryArgs.get(i);
        }

        return this.jdbcTemplate.query(sqlQuery.toString(),
                new CarPartRowMapper(), preparedStatementArgs);
    }

    private static class CarPartRowMapper implements RowMapper<CarPart> {

        @Override
        public CarPart mapRow(ResultSet rs, int rowNum) throws SQLException {
            CarPart newCarPart = new CarPart();

            newCarPart.setId(rs.getInt("ID"));
            newCarPart.setPrice(rs.getBigDecimal("PRICE"));
            newCarPart.setName(rs.getString("NAME"));

            Integer categoryId = rs.getInt("CATEGORY_ID");

            Optional<CarPartCategoryEnum> carPartCategoryOptional
                =   Arrays.stream(CarPartCategoryEnum.values())
                    .filter(cpc -> cpc.ordinal() == categoryId - 1)
                    .findFirst();

            //carPartCategoryOptional.ifPresent(newCarPart::setCategory);

            newCarPart.setDescription(rs.getString("DESCRIPTION"));

            return newCarPart;
        }
    }
}
