package ita3.car_subscription.api;

import ita3.car_subscription.entity.Subscription;
import ita3.car_subscription.repository.ICarRepository;
import ita3.car_subscription.repository.ICustomerRepository;
import ita3.car_subscription.repository.ISubscriptionRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RequestMapping
@RestController
@CrossOrigin("*")
public class SubscriptionController {

    private ISubscriptionRepository subscriptionRepository;
    private ICustomerRepository customerRepository;
    private ICarRepository carRepository;

    public SubscriptionController(ISubscriptionRepository subscriptionRepository, ICustomerRepository customerRepository, ICarRepository carRepository){
        this.subscriptionRepository = subscriptionRepository;
        this.customerRepository = customerRepository;
        this.carRepository = carRepository;

    }

    @GetMapping("/api")
    public String apiInfo(){
        return "Subscription API Endpoints:" +
                "\n- GET /api/subscriptions: Liste af alle lejeaftaler" +
                "\n- GET /api/subscriptions/{id}: Se en lejeaftale ud fra dens ID" +
                "\n- POST /api/subscriptions: Lav en ny lejeaftale" +
                "\n- PUT /api/subscriptions/{id}: Opdater en eksisterende lejeaftale" +
                "\n- DELETE /api/subscriptions/{id}: Slet en lejeaftale";
    }

    //Read all
    @GetMapping("/api/subscriptions")
    public List<Subscription> getAllSubscriptions() {
        return subscriptionRepository.findAll();
    }

    //Read one by id
    @GetMapping("/api/subscription/{id}")
    public ResponseEntity<Subscription> getSubscriptionById(@PathVariable Long id) {
        Optional<Subscription> subscription = subscriptionRepository.findById(id);
        if (subscription.isPresent()) {
            return ResponseEntity.ok(subscription.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //Create a subscription
    @PostMapping("/api/subscriptions")
    public Subscription createSubscription(@RequestBody Subscription subscription) {
        //Her bliver bil og kunde hentet med deres id, som forbinder det til subscribtion
        var car = carRepository.findById(subscription.carID).get();
        var customer = customerRepository.findById(subscription.customerID).get();
        //Her tilknyttes den den fundne bil og kunden til abonnementet ved at bruge "set"
        subscription.setCar(car);
        subscription.setCustomer(customer);
        return subscriptionRepository.save(subscription);
    }

    //Update a subscription
    @PutMapping("/api/subscriptions/{id}")
    public ResponseEntity<Subscription> updatedSubscription(@PathVariable Long id, @RequestBody Subscription subscriptionDetails) {
        Optional<Subscription> existingSubscription = subscriptionRepository.findById(id);
        if (existingSubscription.isPresent()) {
            Subscription updatedSubscription = existingSubscription.get();
            updatedSubscription.setId(subscriptionDetails.getId());
            updatedSubscription.setStartDate(subscriptionDetails.getStartDate());
            updatedSubscription.setEndDate(subscriptionDetails.getEndDate());
            updatedSubscription.setPlannedDistanceInKilometers(subscriptionDetails.getPlannedDistanceInKilometers());
            updatedSubscription.setCustomer(subscriptionDetails.getCustomer());
            updatedSubscription.setCar(subscriptionDetails.getCar());

            return ResponseEntity.ok(subscriptionRepository.save(updatedSubscription));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //Delete a subscription
    @DeleteMapping("/api/subscriptions/{id}")
    public ResponseEntity<?> deleteSubscription(@PathVariable Long id) {
        if (subscriptionRepository.existsById(id)) {
            subscriptionRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
