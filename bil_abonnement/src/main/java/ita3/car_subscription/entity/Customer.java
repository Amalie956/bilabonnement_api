package ita3.car_subscription.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String lastName;
    private int cpr;
    private int accountNumber;
    @OneToMany(mappedBy = "customer")
    private List<Subscription> subscriptions;
    public Customer() {

    }
    public Customer(String firstName, String lastName, int cpr, int accountNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.cpr = cpr;
        this.accountNumber = accountNumber;
    }

    //Gettere og settere
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getCpr() {
        return cpr;
    }

    public void setCpr(int cpr) {
        this.cpr = cpr;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }
}
