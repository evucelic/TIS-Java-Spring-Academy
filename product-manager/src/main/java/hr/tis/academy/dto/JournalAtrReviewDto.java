package hr.tis.academy.dto;

import java.util.Date;

public record JournalAtrReviewDto(
        String attractionLocationName,
        String attractionName,
        String attractionComment,
        String attractionDate
) {
}
