package hr.tis.academy.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "STORE", schema = "PRODUCT_MANAGER")
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long storeId;

    @Column(nullable = false, unique = true)
    private String storeName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumns({
            @JoinColumn(name = "address_city", referencedColumnName = "city"),
            @JoinColumn(name = "address_country", referencedColumnName = "country"),
            @JoinColumn(name = "address_street_name", referencedColumnName = "streetName"),
            @JoinColumn(name = "address_house_number", referencedColumnName = "houseNumber")
    })
    private Address address;

    @Column(nullable = false)
    private String telephoneNumber;

    @Column(nullable = false)
    private String email;

    @OneToMany(mappedBy = "store", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<WorkingDay> workingDays;


    public Store() {
    }

    public Store(String storeName, Address address, String telephoneNumber, String email, List<WorkingDay> workingDays) {
        this.storeName = storeName;
        this.address = address;
        this.telephoneNumber = telephoneNumber;
        this.email = email;
        this.workingDays = workingDays;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<WorkingDay> getWorkingDays() {
        return workingDays;
    }

    public void setWorkingDays(List<WorkingDay> workingDays) {
        this.workingDays = workingDays;
    }
}
