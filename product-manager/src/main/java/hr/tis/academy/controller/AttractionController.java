package hr.tis.academy.controller;

import hr.tis.academy.dto.AttractionDetailsResponse;
import hr.tis.academy.dto.LocationAttractionsResponse;
import hr.tis.academy.dto.PictureDto;
import hr.tis.academy.dto.ReviewResponse;
import hr.tis.academy.model.Picture;
import hr.tis.academy.repository.PictureRepository;
import hr.tis.academy.service.AttractionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/attractions")
public class AttractionController {

    private final PictureRepository pictureRepository;

    private final AttractionService attractionService;

    public AttractionController(AttractionService attractionService, PictureRepository pictureRepository) {
        this.attractionService = attractionService;
        this.pictureRepository = pictureRepository;
    }

    @GetMapping("/{locationName}")
    public ResponseEntity<LocationAttractionsResponse> locationAttractions(@PathVariable String locationName) {
        return new ResponseEntity<>(attractionService.getLocationAttractions(locationName), HttpStatus.OK);
    }

    @PostMapping("/review")
    public ResponseEntity<String> postReview(@RequestBody ReviewResponse reviewResponse) {
        attractionService.makeReview(reviewResponse);
        return new ResponseEntity<>("Review added", HttpStatus.CREATED);
    }

    @GetMapping("/{locationName}/{attractionURLName}")
    public ResponseEntity<AttractionDetailsResponse> attractionDetails(@PathVariable String locationName,
                                                                       @PathVariable String attractionURLName,
                                                                       @RequestParam(value = "excludeReviews", defaultValue = "false") Boolean excludeReviews,
                                                                       @RequestParam(value = "reviewsFrom", defaultValue = "1") Integer reviewsFrom,
                                                                       @RequestParam(value = "reviewsTo", defaultValue = "3") Integer reviewsTo) {

        return new ResponseEntity<>(attractionService.getAttractionDetails(locationName,
                attractionURLName,
                excludeReviews,
                reviewsFrom,
                reviewsTo), HttpStatus.OK);
    }

    @PostMapping("/{location}/{attractionURLName}/picture")
    public ResponseEntity<Long> uploadPhoto(@PathVariable String location, @PathVariable String attractionURLName,
                                            @RequestBody PictureDto pictureDto) {
        attractionService.uploadPhoto(location, attractionURLName, pictureDto);
        Picture picture = pictureRepository.findByPictureURL(pictureDto.pictureURL());
        return new ResponseEntity<>(picture.getPictureId(), HttpStatus.CREATED);
    }

    @GetMapping("/{location}/{attractionURLName}/picture/{pictureId}")
    public ResponseEntity<PictureDto> getPicture(@PathVariable String location,
                                                 @PathVariable String attractionURLName,
                                                 @PathVariable Long pictureId){
        return new ResponseEntity<>(attractionService.getPicture(location, attractionURLName, pictureId), HttpStatus.OK);
    }
}
