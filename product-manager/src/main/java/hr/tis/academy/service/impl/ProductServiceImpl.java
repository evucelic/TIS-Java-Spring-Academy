package hr.tis.academy.service.impl;

import hr.tis.academy.dto.ProductsMetadataDto;
import hr.tis.academy.mapper.ProductsMetadataMapper;
import hr.tis.academy.model.ProductsMetadata;
import hr.tis.academy.repository.ProductRepository;
import hr.tis.academy.repository.ProductRepositoryDB;
import hr.tis.academy.repository.ProductsMetadataRepository;
import hr.tis.academy.scraper.WebScraper;
import hr.tis.academy.service.ProductService;
import org.h2.engine.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductsMetadataRepository productsMetadataRepository;

    private final WebScraper webScraper;

    private final ProductsMetadataMapper productsMetadataMapper;

    public ProductServiceImpl(ProductsMetadataRepository productsMetadataRepository, WebScraper webScraper, ProductsMetadataMapper productsMetadataMapper) {
        this.productsMetadataRepository = productsMetadataRepository;
        this.webScraper = webScraper;
        this.productsMetadataMapper = productsMetadataMapper;
    }

    @Override
    public ProductsMetadataDto getProductsMetadata() {
        try {
            ProductsMetadata productsMetadata = webScraper.fetchProducts();
            return productsMetadataMapper.toDto(productsMetadata);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(ProductsMetadata productsMetadata){
        productsMetadataRepository.save(productsMetadata);
    }

    @Override
    public ProductsMetadataDto getByDate(LocalDate date) {
        return productsMetadataMapper.toDto(productsMetadataRepository.fetchByDate(date));
    }

}
