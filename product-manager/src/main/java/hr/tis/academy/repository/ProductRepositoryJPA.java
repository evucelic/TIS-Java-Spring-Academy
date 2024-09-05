package hr.tis.academy.repository;

import hr.tis.academy.model.Product;
import hr.tis.academy.model.ProductsMetadata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ProductRepositoryJPA extends JpaRepository<Product, Long> {
    Product findByNazivAndOcjena(String naziv, Integer ocjena);

    @Query("select p from Product p where p.naziv = :naziv and p.ocjena = :ocjena")
    Product fetchByNazivAndOcjena(String naziv, Integer ocjena);

    @Query(nativeQuery = true, value =
            "SELECT * FROM PRODUCT_MANAGER.PRODUCT p WHERE p.naziv = :naziv AND p.ocjena = :ocjena")
    ProductsMetadata fetchByTitleAndCreatedTimeNative(String naziv, LocalDateTime ocjena);

}
