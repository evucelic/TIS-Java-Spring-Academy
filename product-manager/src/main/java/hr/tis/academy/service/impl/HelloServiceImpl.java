package hr.tis.academy.service.impl;

import hr.tis.academy.dto.DaysOfWeekResponse;
import hr.tis.academy.dto.IsWeekendResponse;
import hr.tis.academy.service.HelloService;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class HelloServiceImpl implements HelloService {
    @Override
    public DaysOfWeekResponse daysOfWeek() {
        DayOfWeek today = LocalDateTime.now().getDayOfWeek();
        boolean isWeekend = today == DayOfWeek.SATURDAY || today == DayOfWeek.SUNDAY;
        List<DayOfWeek> oddDays = Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY, DayOfWeek.FRIDAY, DayOfWeek.SUNDAY);
        List<DayOfWeek> evenDays = Arrays.asList(DayOfWeek.TUESDAY, DayOfWeek.THURSDAY, DayOfWeek.SATURDAY);
        return new DaysOfWeekResponse(today, isWeekend, oddDays, evenDays);
    }

    @Override
    public String greet(List<String> namesList) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String name: namesList){
            stringBuilder.append("Dobrodošao ");
            stringBuilder.append(name);
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    @Override
    public IsWeekendResponse isWeekend(DayOfWeek day) {
        return new IsWeekendResponse(day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY);
    }
}
