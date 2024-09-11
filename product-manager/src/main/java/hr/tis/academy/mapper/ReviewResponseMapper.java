package hr.tis.academy.mapper;

import hr.tis.academy.dto.AttractionDetailsReviewResponse;
import hr.tis.academy.model.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReviewResponseMapper {

    @Mapping(target = "reviewId", ignore = true)
    @Mapping(target = "attractionReview", ignore = true)
    @Mapping(target = "userReview", ignore = true)
    Review toReviewEntity(AttractionDetailsReviewResponse reviewResponse);

    AttractionDetailsReviewResponse toReviewResponse(Review review);

    List<AttractionDetailsReviewResponse> toReviewResponseList(List<Review> reviewResponseList);
}
