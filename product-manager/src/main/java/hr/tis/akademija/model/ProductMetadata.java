package hr.tis.akademija.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ProductMetadata {
    private long id;
    private LocalDateTime datumVrijemeKreiranja;
    private String naslov;
    private List<Product> popisProizvoda;

    public ProductMetadata(long id, LocalDateTime datumVrijemeKreiranja, String naslov, List<Product> popisProizvoda) {
        this.id = id;
        this.datumVrijemeKreiranja = datumVrijemeKreiranja;
        this.naslov = naslov;
        this.popisProizvoda = popisProizvoda;
    }

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

    public static void main(String[] args) {
        List<Product> lista = new ArrayList<>();
        Product proizvod1 = new Product("mlijeko", new BigDecimal("5"), "EUR");
        Product proizvod2 = new Product("sir", new BigDecimal("10"), "EUR", 5);
        lista.add(proizvod1);
        lista.add(proizvod2);
        ProductMetadata productsMetadata = new ProductMetadata(1, LocalDateTime.now(), "productmetadata",lista);

        System.out.println(productsMetadata);
    }
}
