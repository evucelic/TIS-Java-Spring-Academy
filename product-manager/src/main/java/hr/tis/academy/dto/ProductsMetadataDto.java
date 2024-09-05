package hr.tis.academy.dto;

import hr.tis.academy.model.Product;

import java.time.LocalDateTime;
import java.util.List;

public record ProductsMetadataDto(
        Long id,
        LocalDateTime datumVrijemeKreiranja,
        String naslov,
        List<Product> popisProizvoda
) {
}
