package de.mkienitz.bachelorarbeit.order.domain;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.validation.constraints.NotNull;

public class OrderBillingAddress {
    @NotNull
    @Schema(required = true, example = "Herr")
    private String salutation;

    @NotNull
    @Schema(required = true, example = "Max")
    private String firstName;

    @NotNull
    @Schema(required = true, example = "Mustermann")
    private String lastName;

    @NotNull
    @Schema(required = true, example = "Musterallee")
    private String streetName;

    @NotNull
    @Schema(required = true, example = "42")
    private Integer streetNumber;

    @NotNull
    @Schema(required = true, example = "44141")
    private Integer postalCode;

    @NotNull
    @Schema(required = true, example = "Dortmund")
    private String city;

    @NotNull
    @Schema(required = true, example = "max.mustermann.example.com")
    private String email;

    public OrderBillingAddress() {
    }

    public OrderBillingAddress(String city, String firstName, String lastName, Integer postalCode, String salutation, String streetName, Integer streetNumber, String email) {
        this.salutation = salutation;
        this.firstName = firstName;
        this.lastName = lastName;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.postalCode = postalCode;
        this.city = city;
        this.email = email;
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

    public Integer getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(Integer streetNumber) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "OrderBillingAddress{" +
                "salutation='" + salutation + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", streetName='" + streetName + '\'' +
                ", streetNumber=" + streetNumber +
                ", postalCode=" + postalCode +
                ", city='" + city + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
