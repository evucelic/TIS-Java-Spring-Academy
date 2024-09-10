package hr.tis.academy.dto;

import hr.tis.academy.model.AttractionType;

public record AttractionDto(
        String attractionName,
        String attractionDescription,
        AttractionType attractionType
) {
}
