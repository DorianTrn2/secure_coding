package hr.carparts.store.carpartsstore.repository;

import hr.carparts.store.carpartsstore.model.CarPart;
import hr.carparts.store.carpartsstore.model.CarPartsSearchForm;

import java.util.List;
import java.util.Optional;

public interface CarPartStoreRepository {
    List<CarPart> findAll();
    Optional<CarPart> findById(Integer id);
    void save(CarPart newCarPart);
    List<CarPart> filterByCriteria(CarPartsSearchForm carPartsSearchForm);
}
