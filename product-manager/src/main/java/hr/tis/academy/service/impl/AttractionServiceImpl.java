package hr.tis.academy.service.impl;

import hr.tis.academy.dto.AttractionDto;
import hr.tis.academy.dto.LocationAttractionsResponse;
import hr.tis.academy.model.Attraction;
import hr.tis.academy.model.Location;
import hr.tis.academy.repository.LocationRepository;
import hr.tis.academy.service.AttractionService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AttractionServiceImpl implements AttractionService{

    private final LocationRepository locationRepository;

    public AttractionServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
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
}
