package ita3.car_subscription.repository;

import ita3.car_subscription.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISubscriptionRepository extends JpaRepository<Subscription, Long> {
}
