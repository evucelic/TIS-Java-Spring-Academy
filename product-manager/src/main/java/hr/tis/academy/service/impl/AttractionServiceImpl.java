package hr.tis.academy.service.impl;

import hr.tis.academy.dto.AttractionDto;
import hr.tis.academy.dto.LocationAttractionsResponse;
import hr.tis.academy.dto.ReviewResponse;
import hr.tis.academy.model.Attraction;
import hr.tis.academy.model.Location;
import hr.tis.academy.repository.LocationRepository;
import hr.tis.academy.service.AttractionService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class AttractionServiceImpl implements AttractionService{

    private final ReviewRepository reviewRepository;

    private final AttractionRepository attractionRepository;

    private final LocationRepository locationRepository;

    public AttractionServiceImpl(LocationRepository locationRepository, AttractionRepository attractionRepository, ReviewRepository reviewRepository) {
        this.locationRepository = locationRepository;
        this.attractionRepository = attractionRepository;
        this.reviewRepository = reviewRepository;
    }

    @Override
    public LocationAttractionsResponse getLocationAttractions(String locationName){
        Location location = locationRepository.findByLocationName(locationName);
        List<AttractionDto> attractionDtoList = new ArrayList<>();
        for (Attraction attraction: location.getAttractions()){
            AttractionDto attractionDto = new AttractionDto(
                    attraction.getAttractionName(),
                    attraction.getAttractionDescription(),
                    attraction.getAttractionType());
            attractionDtoList.add(attractionDto);
        }
        return new LocationAttractionsResponse(locationName, attractionDtoList);
    }

    @Override
    public void makeReview(ReviewResponse reviewResponse){
        Attraction existingAttraction = attractionRepository.findByAttractionName(reviewResponse.attractionName());
        if (existingAttraction == null) {
            throw new AttractionNotFoundException("Attraction does not exist");
        } else if (reviewResponse.rating().compareTo(BigDecimal.valueOf(1.0)) < 0
                || reviewResponse.rating().compareTo(BigDecimal.valueOf(5.0)) > 0) {
            throw new RatingNotValidException("Rating is not valid");
        } else {
            Review review;
            review = new Review();

            String stringTime =
                    reviewResponse.timestamp().replace("T", " ").replace("Z", "");

            Timestamp timestamp = Timestamp.valueOf(stringTime);
            review.setReviewText(reviewResponse.reviewText());
            review.setReviewDate(timestamp);
            review.setReviewScore(reviewResponse.rating());
            review.setAttractionReview(existingAttraction);
            reviewRepository.save(review);


        }
    }}
