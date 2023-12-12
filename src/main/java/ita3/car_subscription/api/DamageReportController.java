package ita3.car_subscription.api;

import ita3.car_subscription.entity.DamageReport;
import ita3.car_subscription.repository.IDamageReportRepository;
import ita3.car_subscription.repository.ISubscriptionRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
public class DamageReportController {
    private IDamageReportRepository damageReportRepository;
    private ISubscriptionRepository subscriptionRepository;

    public DamageReportController(IDamageReportRepository damageReportRepository, ISubscriptionRepository subscriptionRepository) {
        this.damageReportRepository = damageReportRepository;
        this.subscriptionRepository = subscriptionRepository;

    }

    @GetMapping("/api/damagereports/info")
    public String apiInfo1(){
        return "Subscription API Endpoints:" +
                "\n- GET /api/subscriptions: Liste af alle skaderapporter" +
                "\n- GET /api/subscriptions/{id}: Se en skaderapport ud fra dens ID" +
                "\n- POST /api/subscriptions: Lav en ny skaderapport" +
                "\n- PUT /api/subscriptions/{id}: Opdater en eksisterende skaderapport" +
                "\n- DELETE /api/subscriptions/{id}: Slet en skaderapport";
    }


    //Read All
    @GetMapping("/api/damagereports")
    public List<DamageReport> getAllDamageReports() {
        return damageReportRepository.findAll();
    }

    //Read one by id
    @GetMapping("/api/damagereports/{id}")
    public ResponseEntity<DamageReport> getDamageReportById(@PathVariable Long id) {
        Optional<DamageReport> damageReport = damageReportRepository.findById(id);
        if (damageReport.isPresent()) {
            return ResponseEntity.ok(damageReport.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //Create a damage report
    @PostMapping("/api/damagereports")
    public DamageReport createDamageReport(@RequestBody DamageReport damageReport) {
        var subscription = subscriptionRepository.findById(damageReport.subscriptionID).get();
        //Her tilknyttes den den fundne bil og kunden til abonnementet ved at bruge "set"
        damageReport.setSubscription(subscription);
        return damageReportRepository.save(damageReport);
    }

    //Update a damage report
    @PutMapping("/api/damagereports/{id}")
    public ResponseEntity<DamageReport> updatedDamageReport(@PathVariable Long id, @RequestBody DamageReport damageReportDetails) {
        Optional<DamageReport> existingDamageReport = damageReportRepository.findById(id);
        if (existingDamageReport.isPresent()) {
            DamageReport updatedDamageReport = existingDamageReport.get();
            updatedDamageReport.setId(damageReportDetails.getId());
            updatedDamageReport.setError(damageReportDetails.getError());
            updatedDamageReport.setErrorType(damageReportDetails.getErrorType());
            updatedDamageReport.setNumbersOfErrors(damageReportDetails.getNumbersOfErrors());
            updatedDamageReport.setPricePerError(damageReportDetails.getPricePerError());

            return ResponseEntity.ok(damageReportRepository.save(updatedDamageReport));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //Delete a damage report
    @DeleteMapping("/api/damageReports/{id}")
    public ResponseEntity<?> deleteDamageReport(@PathVariable Long id) {
        if (damageReportRepository.existsById(id)) {
            damageReportRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }




}
