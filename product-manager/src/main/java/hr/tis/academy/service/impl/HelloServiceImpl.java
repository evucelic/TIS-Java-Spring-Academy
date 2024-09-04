package hr.tis.academy.service.impl;

import hr.tis.academy.dto.DaysOfWeekResponse;
import hr.tis.academy.dto.IsWeekendResponse;
import hr.tis.academy.service.HelloService;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HelloServiceImpl implements HelloService {
    @Override
    public DaysOfWeekResponse daysOfWeek() {
        DayOfWeek today = LocalDateTime.now().getDayOfWeek();
        boolean isweekend = false;
        if (today == DayOfWeek.SATURDAY || today == DayOfWeek.SUNDAY){
            boolean isWeekend = true;
        }
        List<DayOfWeek> oddDays = Arrays.stream(DayOfWeek.values()).filter(day -> day.ordinal() % 2 == 0).toList();
        List<DayOfWeek> evenDays = Arrays.stream(DayOfWeek.values()).filter(day -> day.ordinal() % 2 != 0).toList();
        return new DaysOfWeekResponse(today, isweekend, oddDays, evenDays);
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
