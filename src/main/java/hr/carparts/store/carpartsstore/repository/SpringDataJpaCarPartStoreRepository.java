package hr.carparts.store.carpartsstore.repository;

import hr.carparts.store.carpartsstore.model.CarPart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataJpaCarPartStoreRepository extends JpaRepository<CarPart, Integer> {
}
