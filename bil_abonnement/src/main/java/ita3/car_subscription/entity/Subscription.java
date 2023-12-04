package ita3.car_subscription.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Date startDate;
    private Date endDate;
    private double plannedDistanceInKilometers;
    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id") //fremmednøgle
    private Customer customer;


    @ManyToOne
    @JoinColumn(name = "car_id", referencedColumnName = "id") //fremmednøgle
    private Car car;

    @OneToMany(mappedBy = "subscription")
    private List<Damage> damages;
  
    public Subscription() {
    }

    public Subscription(int startDate, int endDate, double plannedDistanceInKilometers, Customer customer, Car car) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.plannedDistanceInKilometers = plannedDistanceInKilometers;
        this.customer = customer;
        this.car = car;
    }

    //Gettere og settere
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
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
