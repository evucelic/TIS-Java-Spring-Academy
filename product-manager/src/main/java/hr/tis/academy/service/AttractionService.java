package hr.tis.academy.service;

import hr.tis.academy.dto.AttractionDetailsResponse;
import hr.tis.academy.dto.LocationAttractionsResponse;
import hr.tis.academy.dto.PictureDto;
import hr.tis.academy.dto.ReviewResponse;

public interface AttractionService {
    LocationAttractionsResponse getLocationAttractions(String locationName);

    void makeReview(ReviewResponse reviewResponse);

    AttractionDetailsResponse getAttractionDetails(String locationName, String attractionURLName,
                                                   Boolean excludeReviews,
                                                   Integer reviewsFrom,
                                                   Integer reviewsTo);

    void uploadPhoto(String locationName, String attractionURLName, PictureDto pictureDto);

    PictureDto getPicture(String locationName, String attractionURLName, Long pictureId);
}
