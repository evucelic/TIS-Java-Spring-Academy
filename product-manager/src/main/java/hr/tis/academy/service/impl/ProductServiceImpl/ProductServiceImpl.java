package hr.tis.academy.service.impl.ProductServiceImpl;

import hr.tis.academy.model.Product;
import hr.tis.academy.model.ProductsMetadata;
import hr.tis.academy.repository.ProductRepository;
import hr.tis.academy.repository.ProductRepositoryDB;
import hr.tis.academy.scraper.WebScraper;
import hr.tis.academy.service.ProductService;

import java.io.IOException;
import java.time.LocalDate;

public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final WebScraper webScraper = new WebScraper ();

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
