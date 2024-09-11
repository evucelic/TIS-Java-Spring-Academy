package hr.tis.academy.dto;

import java.util.List;

public record JournalDto(
        String startDate,
        String endDate,
        String description,
        List<JournalAtrReviewDto> attractions
) {
}
