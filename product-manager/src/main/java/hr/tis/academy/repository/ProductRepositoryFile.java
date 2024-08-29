package hr.tis.academy.repository;

import hr.tis.academy.file.FileSystemConfiguration;
import hr.tis.academy.model.ProductsMetadata;

import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDate;

import static hr.tis.academy.file.ProductReader.read;
import static hr.tis.academy.file.ProductWriter.writeProducts;

public class ProductRepositoryFile implements ProductRepository {
    File directory = FileSystemConfiguration.PRODUCTS_FILES_FOLDER_PATH.toFile();

    @Override
    public Long insertProducts(ProductsMetadata productsMetadata) {
        productsMetadata.setId(directory.length()+1);
        writeProducts(productsMetadata);
        return productsMetadata.getId();
    }

    @Override
    public BigDecimal fetchSumOfPrices(LocalDate createdDate) {
        ProductsMetadata product = fetchProductsMetadata(createdDate);

        return calculateSumOfPrices(product.getPopisProizvoda());
    }

    @Override
    public BigDecimal fetchSumOfPrices(Long id) {
        return null;
    }

    @Override
    public ProductsMetadata fetchProductsMetadata(LocalDate createdDate) {
        return null;
    }

    @Override
    public ProductsMetadata fetchProductsMetadata(Long id) {
        String[] filesList = directory.list((dir, name) -> name.startsWith(id.toString()));
        assert filesList != null;
        return read(filesList[0]);
    }

    @Override
    public Integer fetchProductsMetadataCount() {
        return (int)directory.length();
    }
}
