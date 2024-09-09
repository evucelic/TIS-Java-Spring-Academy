package hr.tis.academy.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ADDRESS", schema = "PRODUCT_MANAGER")
@Embeddable
public class Address {

    @EmbeddedId
    private AddressId id;

    public Address() {
    }

    public Address(AddressId id) {
        this.id = id;
    }

    public AddressId getId() {
        return id;
    }

    public void setId(AddressId id) {
        this.id = id;
    }
}