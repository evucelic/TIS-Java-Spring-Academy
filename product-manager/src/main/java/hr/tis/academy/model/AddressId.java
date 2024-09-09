package hr.tis.academy.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class AddressId implements Serializable {

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String streetName;

    @Column(nullable = false)
    private String houseNumber;

    public AddressId() {
    }

    public AddressId(String city, String country, String streetName, String houseNumber) {
        this.city = city;
        this.country = country;
        this.streetName = streetName;
        this.houseNumber = houseNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    // Override equals() and hashCode() to compare composite keys correctly

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressId addressId = (AddressId) o;
        return Objects.equals(city, addressId.city) &&
                Objects.equals(country, addressId.country) &&
                Objects.equals(streetName, addressId.streetName) &&
                Objects.equals(houseNumber, addressId.houseNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, country, streetName, houseNumber);
    }
}
