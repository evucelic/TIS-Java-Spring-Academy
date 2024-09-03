package hr.tis.academy.dto;

import java.time.DayOfWeek;
import java.util.List;

public record DaysOfWeekResponse(DayOfWeek today,
                                 boolean isWeekend,
                                 List<DayOfWeek> oddDays,
                                 List<DayOfWeek> evenDays)  {
}
