package ita3.car_subscription.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;
    @JsonFormat(pattern = "yyyy-MM-dd")
    public LocalDate startDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    public LocalDate endDate;
    public double plannedDistanceInKilometers;

    //Flere abonnementer kan tilhøre en kunde
    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id") //fremmednøgle
    public Customer customer;
    public long customerID;

    //Flere abonnementer kan tilhøre en bil
    @ManyToOne
    @JoinColumn(name = "car_id", referencedColumnName = "id") //fremmednøgle
    public Car car;
    public long carID;

    //En abonnement kan tilhøre flere skaderapporter
    @OneToMany(mappedBy = "subscription")
    public List<DamageReport> damageReports;
  
    public Subscription() {
    }

    public Subscription(LocalDate startDate, LocalDate endDate, double plannedDistanceInKilometers, Customer customer, Car car) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.plannedDistanceInKilometers = plannedDistanceInKilometers;
        this.customer = customer;
        this.car = car;
    }

    //Getters og setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public double getPlannedDistanceInKilometers() {
        return plannedDistanceInKilometers;
    }

    public void setPlannedDistanceInKilometers(double plannedDistanceInKilometers) {
        this.plannedDistanceInKilometers = plannedDistanceInKilometers;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
