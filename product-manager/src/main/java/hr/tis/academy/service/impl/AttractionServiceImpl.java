package hr.tis.academy.service.impl;

import hr.tis.academy.dto.*;
import hr.tis.academy.mapper.PictureMapper;
import hr.tis.academy.mapper.ReviewResponseMapper;
import hr.tis.academy.model.Attraction;
import hr.tis.academy.model.Location;
import hr.tis.academy.model.Picture;
import hr.tis.academy.model.Review;
import hr.tis.academy.repository.AttractionRepository;
import hr.tis.academy.repository.LocationRepository;
import hr.tis.academy.repository.PictureRepository;
import hr.tis.academy.repository.ReviewRepository;
import hr.tis.academy.repository.exception.AttractionNotFoundException;
import hr.tis.academy.repository.exception.PictureNotFoundException;
import hr.tis.academy.repository.exception.RatingNotValidException;
import hr.tis.academy.service.AttractionService;
import jakarta.annotation.PostConstruct;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class AttractionServiceImpl implements AttractionService {

    private final PictureMapper pictureMapper;

    private final LocationRepository locationRepository;

    private final PictureRepository pictureRepository;

    private final ReviewRepository reviewRepository;

    private final AttractionRepository attractionRepository;

    private final ReviewResponseMapper reviewResponseMapper;


    public AttractionServiceImpl(LocationRepository locationRepository, AttractionRepository attractionRepository, ReviewRepository reviewRepository, PictureRepository pictureRepository, PictureMapper pictureMapper, ReviewResponseMapper reviewResponseMapper) {
        this.locationRepository = locationRepository;
        this.attractionRepository = attractionRepository;
        this.reviewRepository = reviewRepository;
        this.pictureRepository = pictureRepository;
        this.pictureMapper = pictureMapper;
        this.reviewResponseMapper = reviewResponseMapper;
    }

    @Override
    public LocationAttractionsResponse getLocationAttractions(String locationName) {
        Location location = locationRepository.findByLocationName(locationName);
        List<AttractionDto> attractionDtoList = new ArrayList<>();
        for (Attraction attraction : location.getAttractions()) {
            AttractionDto attractionDto = new AttractionDto(
                    attraction.getAttractionName(),
                    attraction.getAttractionDescription(),
                    attraction.getAttractionType());
            attractionDtoList.add(attractionDto);
        }
        return new LocationAttractionsResponse(locationName, attractionDtoList);
    }

    @Override
    public void makeReview(ReviewResponse reviewResponse) {
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
    }

    @Override
    public AttractionDetailsResponse getAttractionDetails(String locationName, String attractionURLName, Boolean excludeReviews, Integer reviewsFrom, Integer reviewsTo) {

        String rawString = generateAttractionURLName(attractionURLName);
        Attraction attraction = attractionRepository.findByAttractionURLName(rawString);

        if (attraction == null) {
            throw new AttractionNotFoundException("Attraction does not exist");
        }
        List<Location> locations = locationRepository.fetchByLocationName(locationName);

        if (locations.isEmpty()) {
            throw new AttractionNotFoundException("Attraction does not exist");
        }

        boolean locationMatches = locations.stream()
                .anyMatch(location -> Objects.equals(attraction.getAttractionLocation().getLocationId(), location.getLocationId()));

        if (!locationMatches) {
            throw new AttractionNotFoundException("Attraction does not exist in the specified location");
        }

        List<Review> reviews = attraction.getReviews();

        Double averageRating = reviews.stream()
                .map(Review::getReviewScore)
                .reduce(BigDecimal.ZERO, BigDecimal::add).doubleValue() / reviews.size();

        List<AttractionDetailsReviewResponse> attractionDetailsReviewResponseList = reviewResponseMapper.toReviewResponseList(attraction.getReviews());
        if (excludeReviews) attractionDetailsReviewResponseList = null;

        assert attractionDetailsReviewResponseList != null;
        return new AttractionDetailsResponse(attraction.getAttractionName(),
                attraction.getAttractionDescription(),
                attraction.getAttractionType().toString(),
                averageRating,
                attractionDetailsReviewResponseList.subList(reviewsFrom - 1, reviewsTo - 1)
        );
    }

    @Override
    public void uploadPhoto(String locationName, String attractionURLName, PictureDto pictureDto) {
        Location location = locationRepository.findByLocationName(locationName);
        String rawString = generateAttractionURLName(attractionURLName);
        Attraction attraction = attractionRepository.findByAttractionURLNameAndAttractionLocation(
                rawString, location);
        Picture picture = new Picture(pictureDto.pictureURL(), attraction);
        pictureRepository.save(picture);
    }

    @Override
    public PictureDto getPicture(String locationName, String attractionURLName, Long pictureId) {
        Location location = locationRepository.findByLocationName(locationName);
        String rawString = generateAttractionURLName(attractionURLName);
        Attraction attraction = attractionRepository.findByAttractionURLNameAndAttractionLocation(
                rawString, location);
        Picture picture = pictureRepository.findByPictureId(pictureId);
        if (attraction == null) {
            throw new AttractionNotFoundException("Attraction does not exist");
        } else if (picture == null) {
            throw new PictureNotFoundException("Picture does not exist");
        }
        return pictureMapper.toPictureDto(picture);
    }


    @PostConstruct
    private void init() {
        List<Attraction> list = attractionRepository.findAll();
        for (Attraction attraction : list) {
            attraction.setAttractionURLName(generateAttractionURLName(attraction.getAttractionName()));
        }
        attractionRepository.saveAll(list);
    }

    @NotNull
    private String generateAttractionURLName(String name) {
        return Normalizer.normalize(name, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}", "")
                .replace(" ", "%20")
                .replaceAll("[^\\p{ASCII}]", "");
    }

}
