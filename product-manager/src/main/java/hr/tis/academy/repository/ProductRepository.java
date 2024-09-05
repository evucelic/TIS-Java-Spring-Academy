package hr.tis.academy.repository;

import hr.tis.academy.dto.ProductsMetadataDto;
import hr.tis.academy.model.Product;
import hr.tis.academy.model.ProductsMetadata;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface ProductRepository {
    Long insertProducts(ProductsMetadata productsMetadata);

    BigDecimal fetchSumOfPrices(LocalDate createdDate);

    BigDecimal fetchSumOfPrices(Long id);

    ProductsMetadata fetchProductsMetadata(LocalDate createdDate);

    ProductsMetadata fetchProductsMetadata(Long id);

    Integer fetchProductsMetadataCount();

    // dodati i implementirati defaultnu metodu
    default BigDecimal calculateSumOfPrices(List<Product> products) {
        BigDecimal sum = new BigDecimal("0");
        for(Product product : products) {
            sum = sum.add(product.getCijena());
        }
        return sum;
    }

}
