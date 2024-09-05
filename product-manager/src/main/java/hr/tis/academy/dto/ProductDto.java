package hr.tis.academy.dto;

import java.math.BigDecimal;

public record ProductDto(String naziv, BigDecimal cijena, String mjernaJedinica, Integer ocjena) {
}
