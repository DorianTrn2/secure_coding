package hr.carparts.store.carpartsstore.service;

import hr.carparts.store.carpartsstore.dto.CarPartDTO;
import hr.carparts.store.carpartsstore.model.CarPartsSearchForm;

import java.util.List;
import java.util.Optional;

public interface CarPartsStoreService {
    List<CarPartDTO> findAll();
    Optional<CarPartDTO> findById(Integer id);
    void save(CarPartDTO newCarPart);
    List<CarPartDTO> filterByCriteria(CarPartsSearchForm carPartsSearchForm);
}
