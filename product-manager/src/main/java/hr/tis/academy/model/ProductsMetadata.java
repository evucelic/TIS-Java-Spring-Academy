package hr.tis.academy.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PRODUCTS_METADATA", schema = "PRODUCT_MANAGER")
public class ProductsMetadata{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private LocalDateTime datumVrijemeKreiranja;

    @Column
    private String naslov;

    @OneToMany(mappedBy = "productsMetadata", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Product> popisProizvoda = new ArrayList<>();

    public ProductsMetadata(Long id, LocalDateTime datumVrijemeKreiranja, String naslov, List<Product> popisProizvoda) {
        this.id = id;
        this.datumVrijemeKreiranja = datumVrijemeKreiranja;
        this.naslov = naslov;
        this.popisProizvoda = popisProizvoda;
    }

    public ProductsMetadata(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDatumVrijemeKreiranja() {
        return datumVrijemeKreiranja;
    }

    public void setDatumVrijemeKreiranja(LocalDateTime datumVrijemeKreiranja) {
        this.datumVrijemeKreiranja = datumVrijemeKreiranja;
    }

    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public List<Product> getPopisProizvoda() {
        return popisProizvoda;
    }

    public void setPopisProizvoda(List<Product> popisProizvoda) {
        this.popisProizvoda = popisProizvoda;
    }

    @Override
    public String toString() {
        return "ProductMetadata{" +
                "id=" + id +
                ", datumVrijemeKreiranja=" + datumVrijemeKreiranja +
                ", naslov='" + naslov + '\'' +
                ", popisProizvoda=" + popisProizvoda +
                '}';
    }

    /*public static void main(String[] args) {
        List<Product> lista = new ArrayList<>();
        Product proizvod1 = new Product("mlijeko", new BigDecimal("5"), "EUR");
        Product proizvod2 = new Product("sir", new BigDecimal("10"), "EUR", 5);
        lista.add(proizvod1);
        lista.add(proizvod2);
        ProductsMetadata productsMetadata = new ProductsMetadata(1L, LocalDateTime.now(), "productmetadata",lista);

        System.out.println(productsMetadata);
    }*/
}
