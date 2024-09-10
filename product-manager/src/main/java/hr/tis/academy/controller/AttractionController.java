package hr.tis.academy.controller;

import hr.tis.academy.dto.LocationAttractionsResponse;
import hr.tis.academy.dto.ReviewResponse;
import hr.tis.academy.service.AttractionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/attractions")
public class AttractionController {

    private final AttractionService attractionService;

    public AttractionController(AttractionService attractionService) {
        this.attractionService = attractionService;
    }

    @GetMapping("/{locationName}")
    public ResponseEntity<LocationAttractionsResponse> locationAttractions(@PathVariable String locationName){
        return new ResponseEntity<>(attractionService.getLocationAttractions(locationName), HttpStatus.OK);
    }

    @PostMapping("/review")
    public ResponseEntity<String> postReview(@RequestBody ReviewResponse reviewResponse){
        attractionService.makeReview(reviewResponse);
        return new ResponseEntity<>("Review added", HttpStatus.CREATED);
    }
}
