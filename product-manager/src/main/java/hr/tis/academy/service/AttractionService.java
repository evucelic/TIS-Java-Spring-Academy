package hr.tis.academy.service;

import hr.tis.academy.dto.LocationAttractionsResponse;

public interface AttractionService {
    LocationAttractionsResponse getLocationAttractions(String locationName);
}
