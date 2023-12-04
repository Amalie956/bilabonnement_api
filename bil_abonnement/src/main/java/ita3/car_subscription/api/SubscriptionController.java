package ita3.car_subscription.api;

import ita3.car_subscription.entity.Subscription;
import ita3.car_subscription.repository.ISubscriptionRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class SubscriptionController {

    private ISubscriptionRepository subscriptionRepository;

    public SubscriptionController(ISubscriptionRepository subscriptionRepository){
        this.subscriptionRepository = subscriptionRepository;
    }

    @GetMapping("/api")
    public String apiInfo(){
        return "Subscription API Endpoints:" +
                "\n- GET /api/subscriptions: List all subscriptions" +
                "\n- GET /api/subscription/{id}: Get a subscription by its ID" +
                "\n- POST /api/subscription: Create a new movie" +
                "\n- PUT /api/subscription/{id}: Update an existing movie" +
                "\n- DELETE /api/subscription/{id}: Delete a movie";
    }

    @GetMapping("/api/subscriptions")
    public List<Subscription> getAllSubscriptions() {
        return subscriptionRepository.findAll();
    }

    @GetMapping("/api/subscription/{id}")
    public ResponseEntity<Subscription> getSubscriptionById(@PathVariable Long id) {
        Optional<Subscription> subscription = subscriptionRepository.findById(id);
        if (subscription.isPresent()) {
            return ResponseEntity.ok(subscription.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/api/subscription")
    public Subscription createSubscription(@RequestBody Subscription subscription) {
        return subscriptionRepository.save(subscription);
    }

    @PutMapping("/api/subscription/{id}")
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

    @DeleteMapping("/api/subscription/{id}")
    public ResponseEntity<?> deleteSubscription(@PathVariable Long id) {
        if (subscriptionRepository.existsById(id)) {
            subscriptionRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
