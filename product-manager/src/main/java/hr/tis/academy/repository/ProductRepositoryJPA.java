package hr.tis.academy.repository;

import hr.tis.academy.model.Product;
import hr.tis.academy.model.ProductsMetadata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;

public interface ProductRepositoryJPA extends JpaRepository<Product, Long> {

    Product findByNazivAndOcjena(String naziv, int ocjena);

    @Query("select prod from Product prod where prod.naziv = :naziv and " +
            "prod.ocjena = :ocjena")
    Product fetchByNazivAndOcjenaJPQL(String naziv, int ocjena);

    @Query(nativeQuery = true, value =
            "SELECT * FROM PRODUCT_MANAGER.PRODUCTS prod " +
                    "WHERE prod.NAZIV = :naziv AND prod.OCJENA = :ocjena")
    ProductsMetadata fetchByNazivAndOcjenaNative(String naziv, int ocjena);

    @Query("select avg(prod.ocjena) from Product prod where prod.productsMetadata.id = :productsMetadataId")
    Integer fetchAverageIntegerRatingByProductsMetadataId(Long productsMetadataId);

    @Query("select avg(prod.ocjena) from Product prod where prod.productsMetadata.id = :productsMetadataId")
    BigDecimal fetchAverageBigDecimalRatingByProductsMetadataId(Long productsMetadataId);




}
