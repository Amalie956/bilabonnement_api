package ita3.car_subscription.repository;

import ita3.car_subscription.entity.Damage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDamageRepository extends JpaRepository<Damage, Long> {
}
