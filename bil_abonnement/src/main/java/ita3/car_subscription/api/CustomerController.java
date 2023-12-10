package ita3.car_subscription.api;

import ita3.car_subscription.entity.Car;
import ita3.car_subscription.entity.Customer;
import ita3.car_subscription.repository.ICarCustomer;
import ita3.car_subscription.repository.ICustomerRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
public class CustomerController {
    private ICustomerRepository customerRepository;

    public CustomerController(ICustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    //Read All
    @GetMapping("/api/customers")
    public List<Customer> getAllCars() {
        return customerRepository.findAll();
    }

    //Read one by id
    @GetMapping("/api/customers/{id}")
    public ResponseEntity<Customer> getCarById(@PathVariable Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            return ResponseEntity.ok(customer.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //Create a customer
    @PostMapping("/api/customers")
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerRepository.save(customer);
    }

    //Update a customer
    @PutMapping("/api/customers/{id}")
    public ResponseEntity<Customer> updatedSubscription(@PathVariable Long id, @RequestBody Customer customerDetails) {
        Optional<Customer> existingCustomer = customerRepository.findById(id);
        if (existingCustomer.isPresent()) {
           Customer updatedCustomer = existingCustomer.get();
            updatedCustomer.setFirstName(customerDetails.getFirstName());
            updatedCustomer.setLastName(customerDetails.getLastName());
            updatedCustomer.setCpr(customerDetails.getCpr());
            updatedCustomer.setAccountNumber(customerDetails.getAccountNumber());

            return ResponseEntity.ok(customerRepository.save(updatedCustomer));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //Delete a customer
    @DeleteMapping("/api/customers/{id}")
    public ResponseEntity<?> deleteCar(@PathVariable Long id) {
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
