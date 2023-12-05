package ita3.car_subscription.entity;

import jakarta.persistence.*;

@Entity
public class DamageReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String error;
    private String errorType;
    private int numbersOfErrors;
    private double pricePerError;

    @ManyToOne
    @JoinColumn(name = "subscription_id", referencedColumnName = "id") //fremmednøgle
    private Subscription subscription;
    public DamageReport() {
    }

    public DamageReport(String error, String errorType, int numbersOfErrors, int pricePerError) {
        this.error = error;
        this.errorType = errorType;
        this.numbersOfErrors = numbersOfErrors;
        this.pricePerError = pricePerError;
    }

    //Gettere og settere
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

    public int getNumbersOfErrors() {
        return numbersOfErrors;
    }

    public void setNumbersOfErrors(int numbersOfErrors) {
        this.numbersOfErrors = numbersOfErrors;
    }

    public double getPricePerError() {
        return pricePerError;
    }

    public void setPricePerError(double pricePerError) {
        this.pricePerError = pricePerError;
    }
}
