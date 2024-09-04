package hr.tis.academy.dto;

public record AddressDto(
        String city,
        String country,
        String streetName,
        String houseNumber
) {}

