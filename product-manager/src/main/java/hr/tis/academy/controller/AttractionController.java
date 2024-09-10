package hr.tis.academy.controller;

import hr.tis.academy.dto.LocationAttractionsResponse;
import hr.tis.academy.service.AttractionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
