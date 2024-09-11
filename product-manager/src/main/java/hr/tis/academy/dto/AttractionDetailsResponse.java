package hr.tis.academy.dto;

import java.util.List;

public record AttractionDetailsResponse(
        String attractionName,
        String attractionDescription,
        String attractionType,
        Double averageRating,
        List<AttractionDetailsReviewResponse> reviews
) {

}
