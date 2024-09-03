package hr.tis.academy.repository;

import hr.tis.academy.model.Product;
import hr.tis.academy.model.ProductsMetadata;
import hr.tis.academy.repository.exception.NoProductFoundException;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Component("myProductRepositoryInMemory")
@Profile("InMemory")
public class ProductRepositoryInMemory implements ProductRepository {
    private static final List<ProductsMetadata> productsMetadataList = new ArrayList<>();

    public static void main(String[] args) {
        List<Product> lista = new ArrayList<>();
        Product proizvod1 = new Product("mlijeko", new BigDecimal("5"), "EUR");
        Product proizvod2 = new Product("sir", new BigDecimal("10"), "EUR", 5);
        lista.add(proizvod1);
        lista.add(proizvod2);
        ProductsMetadata productsMetadata = new ProductsMetadata(1L, LocalDateTime.now(), "productmetadata", lista);


        ProductRepositoryInMemory productRepositoryInMemory = new ProductRepositoryInMemory();
        productRepositoryInMemory.insertProducts(productsMetadata);


        System.out.println(String.format("%s", productsMetadata.getDatumVrijemeKreiranja()).replace(":", "$"));
    }

    @Override
    public Long insertProducts(ProductsMetadata productsMetadata) {
        productsMetadata.setId((long) (productsMetadataList.size() + 1));
        productsMetadataList.add(productsMetadata);
        return productsMetadata.getId();
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
        try {
            return productsMetadataList.get((int) (id - 1));
        } catch (IndexOutOfBoundsException e) {
            throw new NoProductFoundException("Record doesnt exist.");
        }
    }

    @Override
    public Integer fetchProductsMetadataCount() {
        return productsMetadataList.size();
    }


}
