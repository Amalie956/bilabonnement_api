package ita3.car_subscription.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String brand;
    private String gearType;
    private String fuelType;
    private double perLiter;
    private boolean carAvailable;
    private int registrationNumber;
    private double price;
    @OneToMany(mappedBy = "car")
    private List<Subscription> subscriptions;

    public Car() {

    }
  
    public Car(String brand, String gearType, String fuelType, double perLiter, boolean carAvailable, int registrationNumber, double price) {
        this.brand = brand;
        this.gearType = gearType;
        this.fuelType = fuelType;
        this.perLiter = perLiter;
        this.carAvailable = carAvailable;
        this.registrationNumber = registrationNumber;
        this.price = price;
    }

    //Gettere og settere
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getGearType() {
        return gearType;
    }

    public void setGearType(String gearType) {
        this.gearType = gearType;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public double getPerLiter() {
        return perLiter;
    }

    public void setPerLiter(double perLiter) {
        this.perLiter = perLiter;
    }

    public boolean isCarAvailable() {
        return carAvailable;
    }

    public void setCarAvailable(boolean carAvailable) {
        this.carAvailable = carAvailable;
    }

    public int getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(int registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
