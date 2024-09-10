package hr.tis.academy.repository;

import hr.tis.academy.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long>{

    Location findByLocationName(String locationName);
}
