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

    @Autowired
    private ProductsMetadataRepository productsMetadataRepository;

    @Autowired
    private WebScraper webScraper;

    @Autowired
    private ProductsMetadataMapper productsMetadataMapper;

    public ProductServiceImpl(ProductsMetadataRepository productsMetadataRepository) {
        this.productsMetadataRepository = productsMetadataRepository;
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
    public void save() throws IOException {
        //TODO
        productsMetadataRepository.insert(webScraper.fetchProducts().getNaslov(), webScraper.fetchProducts().getDatumVrijemeKreiranja());
    }

    @Override
    public ProductsMetadataDto getByDate(LocalDate date) {
        return productsMetadataMapper.toDto(productsMetadataRepository.fetchByDate(date));
    }

}
