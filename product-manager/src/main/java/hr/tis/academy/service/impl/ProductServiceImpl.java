package hr.tis.academy.service.impl;

import hr.tis.academy.model.ProductsMetadata;
import hr.tis.academy.repository.ProductRepository;
import hr.tis.academy.repository.ProductRepositoryDB;
import hr.tis.academy.scraper.WebScraper;
import hr.tis.academy.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private WebScraper webScraper;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductsMetadata getProductsMetadata() {
        try {
            return webScraper.fetchProducts();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save() {
        productRepository.insertProducts(getProductsMetadata());
    }

    @Override
    public ProductsMetadata getByDate(LocalDate date) {
        return productRepository.fetchProductsMetadata(date);
    }

    public static void main(String[] args) {
        ProductServiceImpl productService = new ProductServiceImpl(new ProductRepositoryDB());
        productService.save();

    }
}
