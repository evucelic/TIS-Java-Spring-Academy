package hr.tis.academy.service;

import hr.tis.academy.dto.ProductsMetadataDto;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface ProductService {

    List<ProductsMetadataDto> getProductsMetadata();
    void save() throws IOException;
    ProductsMetadataDto getByDate(LocalDate date);
}
