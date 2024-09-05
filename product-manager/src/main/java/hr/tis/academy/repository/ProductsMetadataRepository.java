package hr.tis.academy.repository;

import hr.tis.academy.model.ProductsMetadata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ProductsMetadataRepository extends JpaRepository<ProductsMetadata, Long> {
    ProductsMetadata findByNaslovAndDatumVrijemeKreiranja(String naslov, LocalDateTime datumVrijemeKreiranja);

    @Query("select pm from ProductsMetadata pm where pm.naslov = :title and pm.datumVrijemeKreiranja = :createdTime")
    ProductsMetadata fetchByTitleAndCreatedTimeJPQL(String title, LocalDateTime createdTime);

    @Query(nativeQuery = true, value =
            "SELECT * FROM PRODUCT_MANAGER.PRODUCTS_METADATA pm " +
                    "WHERE pm.NASLOV = :title AND pm.DATUM_VRIJEME_KREIRANJA = :createdTime")
    ProductsMetadata fetchByTitleAndCreatedTimeNative(String title, LocalDateTime createdTime);


}
