package hr.tis.academy.repository;

import hr.tis.academy.model.Attraction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttractionRepository extends JpaRepository<Attraction, Long> {

    Attraction findByAttractionName(String attractionName);
}
