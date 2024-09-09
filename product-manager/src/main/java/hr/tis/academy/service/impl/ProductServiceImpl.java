package hr.tis.academy.service.impl;

import hr.tis.academy.dto.ProductsMetadataDto;
import hr.tis.academy.mapper.ProductsMetadataMapper;
import hr.tis.academy.model.ProductsMetadata;
import hr.tis.academy.repository.ProductsMetadataRepository;
import hr.tis.academy.scraper.WebScraper;
import hr.tis.academy.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductsMetadataRepository productsMetadataRepository;

    @Autowired
    private WebScraper webScraper;

    @Autowired
    private ProductsMetadataMapper productsMetadataMapper;

    @Autowired
    public ProductServiceImpl(ProductsMetadataRepository productsMetadataRepository) {
        this.productsMetadataRepository = productsMetadataRepository;
    }

    @Override
    public List<ProductsMetadataDto> getProductsMetadata() {
        List<ProductsMetadata> productsMetadataList = productsMetadataRepository.findAll();

        return productsMetadataList.stream().map(productsMetadataMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public void save() throws IOException {
        ProductsMetadata productsMetadata = webScraper.fetchProducts();
        productsMetadataRepository.save(productsMetadata);
    }

    @Override
    public ProductsMetadataDto getByDate(LocalDate date) {
        LocalDateTime dateTime = date.atStartOfDay();
        return productsMetadataMapper.toDto(productsMetadataRepository.fetchByTitleAndCreatedTimeNative("getByDate", dateTime));
    }

}
