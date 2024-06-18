package hr.carparts.store.carpartsstore.service;

import hr.carparts.store.carpartsstore.model.CarPart;
import hr.carparts.store.carpartsstore.repository.CarPartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarPartService {

    @Autowired
    private CarPartRepository carPartRepository;

    public List<CarPart> findAll() {
        return carPartRepository.findAll();
    }

    public Optional<CarPart> findById(Long id) {
        return carPartRepository.findById(id);
    }

    public CarPart save(CarPart carPart) {
        return carPartRepository.save(carPart);
    }

    public void deleteById(Long id) {
        carPartRepository.deleteById(id);
    }
}