package hr.tis.academy.repository;

import hr.tis.academy.file.FileSystemConfiguration;
import hr.tis.academy.model.ProductsMetadata;
import hr.tis.academy.repository.exception.NoProductFoundException;

import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;

import static hr.tis.academy.file.ProductReader.read;
import static hr.tis.academy.file.ProductWriter.writeProducts;

public class ProductRepositoryFile implements ProductRepository {
    File directory = FileSystemConfiguration.PRODUCTS_FILES_FOLDER_PATH.toFile();

    @Override
    public Long insertProducts(ProductsMetadata productsMetadata) {
        int fileCount = fetchProductsMetadataCount();
        long newId = fileCount + 1;

        productsMetadata.setId(newId);

        writeProducts(productsMetadata);
        return newId;
    }

    @Override
    public BigDecimal fetchSumOfPrices(LocalDate createdDate) {
        ProductsMetadata product = fetchProductsMetadata(createdDate);

        return calculateSumOfPrices(product.getPopisProizvoda());
    }

    @Override
    public BigDecimal fetchSumOfPrices(Long id) {
        ProductsMetadata product = fetchProductsMetadata(id);

        return calculateSumOfPrices(product.getPopisProizvoda());
    }

    @Override
    public ProductsMetadata fetchProductsMetadata(LocalDate createdDate) {
        String[] filesList = directory.list((dir, name) -> name.contains(createdDate.toString())); // file koji sadrži datum u imenu negdje

        if (filesList == null || filesList.length == 0) {
            throw new NoProductFoundException("Record doesn’t exist.");
        }

        Optional<String> latestFileName = Arrays.stream(filesList)
                .max(Comparator.comparing(this::parseLocalDateFromFilename));

        return read(latestFileName.get());
    }

    @Override
    public ProductsMetadata fetchProductsMetadata(Long id) {
        String[] filesList = directory.list((dir, name) -> name.startsWith(id.toString()));
        assert filesList != null;
        return read(filesList[0]);
    }

    @Override
    public Integer fetchProductsMetadataCount() {
        String[] filesList = directory.list((dir, name) -> name.endsWith(".txt"));
        return filesList != null ? filesList.length : 0;
    }

    // Pomoćna funkcija za parsiranje datuma iz imena filea
    private LocalDate parseLocalDateFromFilename(String filename){
        String[] parts = filename.split("_");
        LocalDateTime dateTime = LocalDateTime.parse(parts[1].replace("$",":"));

        return dateTime.toLocalDate();
    }
}
