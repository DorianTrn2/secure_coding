package hr.carparts.store.carpartsstore.service;

import hr.carparts.store.carpartsstore.dto.CarPartDTO;
import hr.carparts.store.carpartsstore.model.CarPart;
import hr.carparts.store.carpartsstore.model.CarPartCategory;
import hr.carparts.store.carpartsstore.model.CarPartsSearchForm;
import hr.carparts.store.carpartsstore.repository.SpringDataJpaCarPartCategoryRepository;
import hr.carparts.store.carpartsstore.repository.SpringDataJpaCarPartStoreRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CarPartsStoreServiceImpl implements CarPartsStoreService {

    private SpringDataJpaCarPartCategoryRepository carPartCategoryRepository;
    private SpringDataJpaCarPartStoreRepository carPartRepository;

    @Override
    public List<CarPartDTO> findAll() {
        return carPartRepository.findAll()
                .stream().map(this::convertCarPartToCarPartDto)
                .toList();
    }

    @Override
    public Optional<CarPartDTO> findById(Integer id) {
        return carPartRepository.findById(id).stream()
                .map(this::convertCarPartToCarPartDto)
                .findFirst();
    }

    @Override
    public void save(CarPartDTO newCarPart) {
        carPartRepository.save(convertCarPartDtoToCarPart(newCarPart));
    }

    @Override
    public List<CarPartDTO> filterByCriteria(CarPartsSearchForm carPartsSearchForm) {

        CarPart filterCarPart = new CarPart();
        String carPartName = carPartsSearchForm.getName();
        filterCarPart.setName(carPartName);
        Example<CarPart> carPartExample = Example.of(filterCarPart);

        return carPartRepository.findAll(carPartExample)
                .stream()
                .map(this::convertCarPartToCarPartDto)
                .toList();
    }

    private CarPartDTO convertCarPartToCarPartDto(CarPart carPart) {
        return new CarPartDTO(
                carPart.getName(),
                carPart.getCategory().getName(),
                carPart.getPrice(),
                carPart.getDescription()
        );
    }

    private CarPart convertCarPartDtoToCarPart(CarPartDTO carPartDTO) {

        List<CarPartCategory> carPartCategoryList =
                carPartCategoryRepository.findByName(carPartDTO.getName());


        CarPartCategory carPartCategory=null;

        if(!carPartCategoryList.isEmpty()){
            carPartCategory=carPartCategoryList.get(0);
        }

        return new CarPart(
                carPartDTO.getName(),
                carPartCategory,
                carPartDTO.getPrice(),
                carPartDTO.getDescription()
        );
    }
}
