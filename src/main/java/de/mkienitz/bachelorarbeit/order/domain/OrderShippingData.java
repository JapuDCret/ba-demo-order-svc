package de.mkienitz.bachelorarbeit.order.domain;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.validation.constraints.NotNull;

public class OrderShippingData {
    @NotNull
    @Schema(required = true, example = "Herr")
    private String salutation;

    @NotNull
    @Schema(required = true, example = "Peter")
    private String firstName;

    @NotNull
    @Schema(required = true, example = "Mustermann")
    private String lastName;

    @NotNull
    @Schema(required = true, example = "Musterallee")
    private String streetName;

    @NotNull
    @Schema(required = true, example = "42")
    private String streetNumber;

    @NotNull
    @Schema(required = true, example = "44141")
    private Integer postalCode;

    @NotNull
    @Schema(required = true, example = "Dortmund")
    private String city;

    public OrderShippingData() {
    }

    public OrderShippingData(String salutation, String firstName, String lastName, String streetName, String streetNumber, Integer postalCode, String city) {
        this.salutation = salutation;
        this.firstName = firstName;
        this.lastName = lastName;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.postalCode = postalCode;
        this.city = city;
    }

    public String getSalutation() {
        return salutation;
    }

    public void setSalutation(String salutation) {
        this.salutation = salutation;
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

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public Integer getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(Integer postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "OrderShippingData{" +
                "salutation='" + salutation + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", streetName='" + streetName + '\'' +
                ", streetNumber='" + streetNumber + '\'' +
                ", postalCode=" + postalCode +
                ", city='" + city + '\'' +
                '}';
    }
}
