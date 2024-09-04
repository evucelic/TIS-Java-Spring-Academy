package hr.tis.academy.service;

import hr.tis.academy.dto.DaysOfWeekResponse;
import hr.tis.academy.dto.IsWeekendResponse;

import java.time.DayOfWeek;
import java.util.List;


public interface HelloService {
    DaysOfWeekResponse daysOfWeek();

    String greet(List<String> namesList);

    IsWeekendResponse isWeekend(DayOfWeek day);

    byte[] createImage(String text, int width, int height, int red, int green, int blue);
}
