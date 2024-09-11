package hr.tis.academy.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;

public record AttractionDetailsReviewResponse(
        Timestamp reviewDate,
        BigDecimal reviewScore,
        String reviewText
) {
}
