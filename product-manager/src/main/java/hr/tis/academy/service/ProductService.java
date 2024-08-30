package hr.tis.academy.service;

import hr.tis.academy.model.Product;
import hr.tis.academy.model.ProductsMetadata;

import java.time.LocalDate;

public interface ProductService {

    ProductsMetadata getProductsMetadata();
    void save();
    ProductsMetadata getByDate(LocalDate date);
}
