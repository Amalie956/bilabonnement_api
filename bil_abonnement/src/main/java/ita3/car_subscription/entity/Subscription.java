package ita3.car_subscription.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int startDate;
    private int endDate;
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
    public Subscription(long id, int startDate, int endDate, double plannedDistanceInKilometers, Customer customer, Car car) {
        this.id = id;
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

    public int getStartDate() {
        return startDate;
    }

    public void setStartDate(int startDate) {
        this.startDate = startDate;
    }

    public int getEndDate() {
        return endDate;
    }

    public void setEndDate(int endDate) {
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
