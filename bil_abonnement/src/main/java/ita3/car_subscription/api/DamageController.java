package ita3.car_subscription.api;

import ita3.car_subscription.repository.IDamageRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DamageController {
    private IDamageRepository damageRepository;

    public DamageController(IDamageRepository damageRepository) {
        this.damageRepository = damageRepository;
    }

}
