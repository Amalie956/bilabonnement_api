package ita3.car_subscription.api;

import ita3.car_subscription.repository.ICustomerRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {
    private ICustomerRepository customerRepository;

    public CustomerController(ICustomerRepository customerRepository) {
        this.customerRepository = customerRepository;

    }

}
