package hr.tis.akademija.repository;

import hr.tis.academy.repository.exception.NoProductFoundException;
import hr.tis.akademija.model.Product;
import hr.tis.akademija.model.ProductsMetadata;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class ProductRepositoryInMemory implements ProductRepository {
    private static final List<ProductsMetadata> productsMetadataList = new ArrayList<>();

    @Override
    public Long insertProducts(ProductsMetadata productsMetadata) {
        productsMetadata.setId(Long.valueOf(productsMetadataList.size()+1));
        productsMetadataList.add(productsMetadata);
        return productsMetadata.getId();
    }

    @Override
    public BigDecimal fetchSumOfPrices(LocalDate createdDate) {

        ProductsMetadata product = fetchProductsMetadata(createdDate);
        BigDecimal bigDecimal = calculateSumOfPrices(product.getPopisProizvoda());

        return bigDecimal;
    }

    @Override
    public BigDecimal fetchSumOfPrices(Long id) {

        ProductsMetadata product = fetchProductsMetadata(id);

        BigDecimal bigDecimal = calculateSumOfPrices(product.getPopisProizvoda());
        return bigDecimal;
    }

    @Override
    public ProductsMetadata fetchProductsMetadata(LocalDate createdDate) {
        Optional<ProductsMetadata> product = productsMetadataList.stream()
                .filter(list -> list.getDatumVrijemeKreiranja().toLocalDate().equals(createdDate))
                .max(Comparator.comparing(ProductsMetadata::getDatumVrijemeKreiranja));
        if (product.isEmpty()) {
            throw new NoProductFoundException("Record doesn’t exist.");
        }

        return product.orElse(null);
    }

    @Override
    public ProductsMetadata fetchProductsMetadata(Long id) {
        Optional<ProductsMetadata> product = productsMetadataList.stream().filter(list -> list.getId().equals(id)).findFirst();
        if (product.isEmpty()) {
            throw new NoProductFoundException("Record doesn’t exist.");
        }

        return product.orElse(null);
    }

    @Override
    public Integer fetchProductsMetadataCount() {
        return productsMetadataList.size();
    }

    public static void main(String[] args) {
        List<Product> lista = new ArrayList<>();
        Product proizvod1 = new Product("mlijeko", new BigDecimal("5"), "EUR");
        Product proizvod2 = new Product("sir", new BigDecimal("10"), "EUR", 5);
        lista.add(proizvod1);
        lista.add(proizvod2);
        ProductsMetadata productsMetadata = new ProductsMetadata(1L, LocalDateTime.now(), "productmetadata",lista);


        ProductRepositoryInMemory productRepositoryInMemory = new ProductRepositoryInMemory();
        productRepositoryInMemory.insertProducts(productsMetadata);

        System.out.println(productRepositoryInMemory.fetchProductsMetadata(2L));
    }


}
