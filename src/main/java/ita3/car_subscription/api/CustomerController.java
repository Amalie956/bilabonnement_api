package ita3.car_subscription.api;

import ita3.car_subscription.entity.Customer;
import ita3.car_subscription.repository.ICustomerRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RequestMapping
@RestController
@CrossOrigin("*")
public class CustomerController {
    public ICustomerRepository customerRepository;

    public CustomerController(ICustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    //Read All -> GET
    @GetMapping("/api/customers")
    public List<Customer> getAllCars() {
        return customerRepository.findAll();
    }

    //Read one by id -> GET
    @GetMapping("/api/customers/{id}")
    public ResponseEntity<Customer> getCarById(@PathVariable Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            return ResponseEntity.ok(customer.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
