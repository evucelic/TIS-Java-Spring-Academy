package hr.tis.academy.repository;

import hr.tis.academy.file.FileSystemConfiguration;
import hr.tis.academy.model.Product;
import hr.tis.academy.model.ProductsMetadata;
import hr.tis.academy.repository.exception.NoProductFoundException;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static hr.tis.academy.file.ProductReader.read;
import static hr.tis.academy.file.ProductWriter.writeProducts;

@Component
@Profile("File")
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

    public static void main(String[] args) {
        List<Product> lista = new ArrayList<>();
        Product proizvod1 = new Product("mlijeko", new BigDecimal("5"), "EUR");
        Product proizvod2 = new Product("sir", new BigDecimal("10"), "EUR", 5);
        Product proizvod3 = new Product("kava", new BigDecimal("1"), "EUR", 3);
        lista.add(proizvod1);
        lista.add(proizvod2);
        lista.add(proizvod3);
        ProductsMetadata productsMetadata = new ProductsMetadata(1L, LocalDateTime.now(), "productmetadata",lista);


        ProductRepositoryFile productRepositoryFile = new ProductRepositoryFile();
        //productRepositoryFile.insertProducts(productsMetadata);


        System.out.println(String.format("%s", productsMetadata.getDatumVrijemeKreiranja()).replace(":", "$"));
        System.out.println(productRepositoryFile.fetchSumOfPrices(2L));
    }
}
