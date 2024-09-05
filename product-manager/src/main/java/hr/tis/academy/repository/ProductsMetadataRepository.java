package hr.tis.academy.repository;

import hr.tis.academy.model.ProductsMetadata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Repository
public interface ProductsMetadataRepository extends JpaRepository<ProductsMetadata, Long> {
    ProductsMetadata findByNaslovAndDatumVrijemeKreiranja(String naslov, LocalDateTime datumVrijemeKreiranja);

    @Query("select pm from ProductsMetadata pm where pm.naslov = :title and " +
            "pm.datumVrijemeKreiranja = :createdTime")
    ProductsMetadata fetchByTitleAndCreatedTimeJPQL(String title, LocalDateTime createdTime);

    @Query(nativeQuery = true, value =
            "SELECT * FROM PRODUCT_MANAGER.PRODUCTS_METADATA pm " +
                    "WHERE pm.NASLOV = :title AND pm.DATUM_VRIJEME_KREIRANJA = :createdTime")
    ProductsMetadata fetchByTitleAndCreatedTimeNative(String title, LocalDateTime createdTime);

    @Query(nativeQuery = true, value ="SELECT * FROM PRODUCTS_METADATA WHERE DATUM_VRIJEME_KREIRANJA BETWEEN ? AND ?")
    ProductsMetadata fetchProductsRecord(LocalDateTime startDate, LocalDateTime endDate);

    @Query(nativeQuery = true, value = "SELECT * FROM PRODUCT_MANAGER.PRODUCTS_METADATA WHERE CAST(DATUM_VRIJEME_KREIRANJA AS DATE) IS ?")
    ProductsMetadata fetchByDate(LocalDate createdTime);

    @Modifying
    @Query(nativeQuery = true, value = "INSERT INTO PRODUCT_MANAGER.PRODUCTS_METADATA (NASLOV, DATUM_VRIJEME_KREIRANJA) VALUES (?, ?)")
    void insert(String naslov, LocalDateTime createdDateTime);

}
