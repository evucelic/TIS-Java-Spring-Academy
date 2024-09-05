package hr.tis.academy.service.impl;

import hr.tis.academy.dto.ProductsMetadataDto;
import hr.tis.academy.mapper.ProductsMetadataMapper;
import hr.tis.academy.repository.ProductRepository;
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

    @Autowired
    private ProductsMetadataMapper productsMetadataMapper;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductsMetadataDto getProductsMetadata() {
        try {
            return productsMetadataMapper.toDto(webScraper.fetchProducts());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save() throws IOException {
        productRepository.insertProducts(webScraper.fetchProducts());
    }

    @Override
    public ProductsMetadataDto getByDate(LocalDate date) {
        return productsMetadataMapper.toDto(productRepository.fetchProductsMetadata(date));
    }

//    public static void main(String[] args) {
//        ProductServiceImpl productService = new ProductServiceImpl(new ProductRepositoryDB());
//        productService.save();
//
//    }
}
