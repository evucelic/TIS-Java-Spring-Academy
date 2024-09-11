package hr.tis.academy.repository;

import hr.tis.academy.model.Attraction;
import hr.tis.academy.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttractionRepository extends JpaRepository<Attraction, Long> {

    Attraction findByAttractionName(String attractionName);
    Attraction findByAttractionURLName(String attractionURLName);
    Attraction findByAttractionURLNameAndAttractionLocation(String attractionURLName, Location attractionLocation);
}
