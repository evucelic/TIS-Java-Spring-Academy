package hr.tis.academy.service;

import hr.tis.academy.dto.ProductsMetadataDto;
import hr.tis.academy.model.Product;
import hr.tis.academy.model.ProductsMetadata;

import java.io.IOException;
import java.time.LocalDate;

public interface ProductService {

    ProductsMetadataDto getProductsMetadata();
    void save() throws IOException;
    ProductsMetadataDto getByDate(LocalDate date);
}
