package hr.tis.academy.dto;

import java.util.List;

public record LocationAttractionsResponse(
        String locationName,
        List<AttractionDto> attractionDtoList
) {
}
