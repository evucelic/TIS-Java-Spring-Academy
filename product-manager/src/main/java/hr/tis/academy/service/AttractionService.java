package hr.tis.academy.service;

import hr.tis.academy.dto.LocationAttractionsResponse;
import hr.tis.academy.dto.ReviewResponse;

public interface AttractionService {
    LocationAttractionsResponse getLocationAttractions(String locationName);

    void makeReview(ReviewResponse reviewResponse);
}
