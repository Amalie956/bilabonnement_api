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

    //Create a car -> POST
    @PostMapping("/api/cars")
    public Car createCar(@RequestBody Car car) {
        return carRepository.save(car);
    }

    //Update a car -> PUT
    @PutMapping("/api/cars/{id}")
    public ResponseEntity<Car> updatedSubscription(@PathVariable Long id, @RequestBody Car carDetails) {
        Optional<Car> existingCar = carRepository.findById(id);
        if (existingCar.isPresent()) {
            Car updatedCar = existingCar.get();
            updatedCar.setBrand(carDetails.getBrand());
            updatedCar.setGearType(carDetails.getGearType());
            updatedCar.setFuelType(carDetails.getFuelType());
            updatedCar.setPerLiter(carDetails.getPerLiter());
            updatedCar.setCarAvailable(carDetails.isCarAvailable());
            updatedCar.setRegistrationNumber(carDetails.getRegistrationNumber());
            updatedCar.setPrice(carDetails.getPrice());
            return ResponseEntity.ok(carRepository.save(updatedCar));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //Delete a car -> DELETE
    @DeleteMapping("/api/cars/{id}")
    public ResponseEntity<?> deleteCar(@PathVariable Long id) {
        if (carRepository.existsById(id)) {
            carRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
