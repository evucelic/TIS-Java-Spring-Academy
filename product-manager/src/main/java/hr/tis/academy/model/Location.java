package hr.tis.academy.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "LOCATIONS", schema = "SIGHTSEEING")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long locationId;

    @Column(nullable = false)
    private String locationName;

    @OneToMany(mappedBy = "attractionLocation", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Attraction> attractions;

    public Location() {
    }

    public Location(Long locationId, String locationName, List<Attraction> attractions) {
        this.locationId = locationId;
        this.locationName = locationName;
        this.attractions = attractions;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public List<Attraction> getAttractions() {
        return attractions;
    }

    public void setLocations(List<Attraction> attractions) {
        this.attractions = attractions;
    }
}
