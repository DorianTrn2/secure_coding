package hr.carparts.store.carpartsstore.repository;

import hr.carparts.store.carpartsstore.model.CarPartCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpringDataJpaCarPartCategoryRepository extends JpaRepository<CarPartCategory, Integer> {
    List<CarPartCategory> findByName(String name);
}
