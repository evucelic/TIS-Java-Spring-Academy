package hr.tis.academy.service;

import hr.tis.academy.dto.DaysOfWeekResponse;

import java.util.List;


public interface HelloService {
    DaysOfWeekResponse daysOfWeek();

    String greet(List<String> namesList);
}
