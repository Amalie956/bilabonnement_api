package ita3.car_subscription.repository;

import ita3.car_subscription.entity.DamageReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDamageReportRepository extends JpaRepository<DamageReport, Long> {
}
