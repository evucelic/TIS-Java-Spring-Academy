package hr.tis.academy.dto;

public record WorkingDayDto(
        String dayOfWeek,
        String startTime,
        String endTime
) {}
