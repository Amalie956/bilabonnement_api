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
    public ISubscriptionRepository subscriptionRepository;
    public ICustomerRepository customerRepository;
    public ICarRepository carRepository;

    public SubscriptionController(ISubscriptionRepository subscriptionRepository, ICustomerRepository customerRepository, ICarRepository carRepository){
        this.subscriptionRepository = subscriptionRepository;
        this.customerRepository = customerRepository;
        this.carRepository = carRepository;
    }

    //Read about API endpoints -> GET
    @GetMapping("/api")
    public String apiInfo(){
        return "Subscription API Endpoints:" +
                "\n- GET /api/subscriptions: Se alle abonnementer" +
                "\n- GET /api/subscriptions/{id}: Se et abonnement ud fra dens ID" +
                "\n- POST /api/subscriptions: Lav et ny abonnement" +
                "\n- PUT /api/subscriptions/{id}: Opdater et eksisterende abonnement" +
                "\n- DELETE /api/subscriptions/{id}: Slet et abonnement";
    }

    //Read all -> GET
    @GetMapping("/api/subscriptions")
    public List<Subscription> getAllSubscriptions() {
        return subscriptionRepository.findAll();
    }

    //Read one by id -> GET
    @GetMapping("/api/subscription/{id}")
    public ResponseEntity<Subscription> getSubscriptionById(@PathVariable Long id) {
        Optional<Subscription> subscription = subscriptionRepository.findById(id);
        if (subscription.isPresent()) {
            return ResponseEntity.ok(subscription.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    //Create a subscription -> POST
    @PostMapping("/api/subscriptions")
    public Subscription createSubscription(@RequestBody Subscription subscription) {
        //Her bliver bil og kunde hentet med givet id, som forbinder det til abonnement
        var car = carRepository.findById(subscription.carID).get();
        var customer = customerRepository.findById(subscription.customerID).get();
        //Abonnement bliver tilknyttet til bil og kunde, ved at brug af "set"
        subscription.setCar(car);
        subscription.setCustomer(customer);
        return subscriptionRepository.save(subscription);
    }

    //Update a subscription -> PUT
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
        try {
            if (subscriptionRepository.existsById(id)) {
                subscriptionRepository.deleteById(id);
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            throw e;
        }
    }

}
