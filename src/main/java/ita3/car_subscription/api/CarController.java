package ita3.car_subscription.api;

import ita3.car_subscription.entity.Car;
import ita3.car_subscription.repository.ICarRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RequestMapping
@RestController
@CrossOrigin("*")
public class CarController {
    public ICarRepository carRepository;

    public CarController(ICarRepository carRepository) {
        this.carRepository = carRepository;
    }

    //Read all -> GET
    @GetMapping("/api/cars")
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    //Read one by id -> GET
    @GetMapping("/api/cars/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable Long id) {
        Optional<Car> car = carRepository.findById(id);
        if (car.isPresent()) {
            return ResponseEntity.ok(car.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
