package hr.tis.academy.repository;

import hr.tis.academy.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LocationRepository extends JpaRepository<Location, Long>{

    Location findByLocationName(String locationName);
    Location findByLocationId(Long locationId);

    @Query(nativeQuery = true, value = "SELECT * FROM SIGHTSEEING.LOCATIONS loc WHERE loc.LOCATION_NAME = :locationName")
    List<Location> fetchByLocationName(String locationName);

}
