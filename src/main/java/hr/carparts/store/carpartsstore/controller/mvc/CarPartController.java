package hr.carparts.store.carpartsstore.controller.mvc;

import hr.carparts.store.carpartsstore.model.CarPart;
import hr.carparts.store.carpartsstore.service.CarPartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/mvc/carPart")
public class CarPartController {

    @Autowired
    private CarPartService carPartService;

    @Secured("ROLE_USER")
    @GetMapping
    public List<CarPart> getAllCarParts() {
        return carPartService.findAll();
    }

    @Secured("ROLE_USER")
    @GetMapping("/{id}")
    public ResponseEntity<CarPart> getCarPartById(@PathVariable Long id) {
        Optional<CarPart> carPart = carPartService.findById(id);
        return carPart.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Secured("ROLE_ADMIN")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public CarPart createCarPart(@RequestBody CarPart carPart) {
        return carPartService.save(carPart);
    }

    @Secured("ROLE_ADMIN")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<CarPart> updateCarPart(@PathVariable Long id, @RequestBody CarPart carPartDetails) {
        Optional<CarPart> carPart = carPartService.findById(id);

        if (carPart.isPresent()) {
            CarPart updatedCarPart = carPart.get();
            updatedCarPart.setName(carPartDetails.getName());
            updatedCarPart.setPrice(carPartDetails.getPrice());
            carPartService.save(updatedCarPart);
            return ResponseEntity.ok(updatedCarPart);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Secured("ROLE_ADMIN")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCarPart(@PathVariable Long id) {
        carPartService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}