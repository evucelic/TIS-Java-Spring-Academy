package hr.tis.academy.dto;

import java.sql.Timestamp;
import java.math.BigDecimal;

public record ReviewResponse(
        String location,
        String attractionName,
        String timestamp,
        BigDecimal rating,
        String reviewText
) {
}
