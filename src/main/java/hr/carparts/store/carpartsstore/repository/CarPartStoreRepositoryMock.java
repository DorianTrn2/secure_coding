package hr.carparts.store.carpartsstore.repository;

import hr.carparts.store.carpartsstore.model.CarPart;
import hr.carparts.store.carpartsstore.model.CarPartCategoryEnum;
import hr.carparts.store.carpartsstore.model.CarPartsSearchForm;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CarPartStoreRepositoryMock implements CarPartStoreRepository {

    private static List<CarPart> carPartList;

    static {
        carPartList = new ArrayList<>();

        CarPart firstCarPart = new CarPart();
        firstCarPart.setId(1);
        firstCarPart.setName("Winter tires");
       // firstCarPart.setCategory(CarPartCategoryEnum.CAR_PART);
        firstCarPart.setDescription("235/45/R18");
        firstCarPart.setPrice(new BigDecimal(150));

        CarPart secondCarPart = new CarPart();
        secondCarPart.setId(2);
        secondCarPart.setName("Motor oil");
        //secondCarPart.setCategory(CarPartCategoryEnum.OIL);
        secondCarPart.setDescription("10W 40");
        secondCarPart.setPrice(new BigDecimal(20));

        CarPart thirdCarPart = new CarPart();
        thirdCarPart.setId(3);
        thirdCarPart.setName("Tire chains for winter conditions");
        //thirdCarPart.setCategory(CarPartCategoryEnum.ACCESSORIES);
        thirdCarPart.setDescription("235/45/R18");
        thirdCarPart.setPrice(new BigDecimal(40));

        CarPart fourthCarPart = new CarPart();
        fourthCarPart.setId(4);
        fourthCarPart.setName("Carglass clean fluid");
        //fourthCarPart.setCategory(CarPartCategoryEnum.OTHER);
        fourthCarPart.setDescription("Winter mix");
        fourthCarPart.setPrice(new BigDecimal(16));

        carPartList.add(firstCarPart);
        carPartList.add(secondCarPart);
        carPartList.add(thirdCarPart);
        carPartList.add(fourthCarPart);
    }

    @Override
    public List<CarPart> findAll() {
        return carPartList;
    }

    @Override
    public Optional<CarPart> findById(Integer id) {
        return carPartList.stream()
                .filter(cp -> cp.getId().equals(id))
                .findFirst();
    }

    @Override
    public void save(CarPart newCarPart) {
        newCarPart.setId(generateNewId());
        carPartList.add(newCarPart);
    }

    @Override
    public List<CarPart> filterByCriteria(CarPartsSearchForm carPartsSearchForm) {
        List<CarPart> carParts = findAll();

        if(!carPartsSearchForm.getName().isEmpty()) {
            carParts = carParts.stream()
                    .filter(cp -> cp.getName().toLowerCase().
                            contains(carPartsSearchForm.getName().toLowerCase()))
                    .toList();
        }

        /*if(!carPartsSearchForm.getCategory().isEmpty()) {
            carParts = carParts.stream()
                    .filter(cp -> cp.getCategory().name()
                            .equals(carPartsSearchForm.getCategory()))
                    .toList();
        }*/

        if(Optional.ofNullable(carPartsSearchForm.getLowerPrice()).isPresent()) {
            carParts = carParts.stream()
                    .filter(cp -> cp.getPrice().compareTo(carPartsSearchForm.getLowerPrice()) >= 0)
                    .toList();
        }

        if(Optional.ofNullable(carPartsSearchForm.getUpperPrice()).isPresent()) {
            carParts = carParts.stream()
                    .filter(cp -> cp.getPrice().compareTo(carPartsSearchForm.getUpperPrice()) <= 0)
                    .toList();
        }

        if(!carPartsSearchForm.getDescription().isEmpty()) {
            carParts = carParts.stream()
                    .filter(cp -> cp.getDescription().toLowerCase().
                            contains(carPartsSearchForm.getDescription().toLowerCase()))
                    .toList();
        }

        return carParts;
    }

    public Integer generateNewId() {
        Optional<Integer> maxId =
                carPartList.stream()
                        .max((cp1, cp2) -> cp1.getId().compareTo(cp2.getId()))
                        .map(CarPart::getId);
        return maxId.map(integer -> integer + 1).orElse(1);
    }
}
