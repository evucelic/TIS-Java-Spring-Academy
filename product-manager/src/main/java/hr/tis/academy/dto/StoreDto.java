package hr.tis.academy.dto;

import java.util.List;

public record StoreDto(
        String storeName,
        AddressDto address,
        String telephoneNumber,
        String email,
        List<WorkingDayDto> workingDays
) {}
