package hr.tis.academy.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "PRODUCTS", schema = "PRODUCT_MANAGER")
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String naziv;

    @Column
    private BigDecimal cijena;

    @Column
    private String mjernaJedinica;

    @Column
    private Integer ocjena = 0;

    @ManyToOne
    @JoinColumn(name = "PRODUCTS_METADATA_ID", nullable = false)
    private ProductsMetadata productsMetadata;


    public Product(String naziv, BigDecimal cijena, String mjernaJedinica) {
        this.naziv = naziv;
        this.cijena = cijena;
        this.mjernaJedinica = mjernaJedinica;
    }

    public Product(String naziv, BigDecimal cijena, String mjernaJedinica, Integer ocjena) {
        this.naziv = naziv;
        this.cijena = cijena;
        this.mjernaJedinica = mjernaJedinica;
        this.ocjena = ocjena;
    }

    public Product(){}

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public BigDecimal getCijena() {
        return cijena;
    }

    public void setCijena(BigDecimal cijena) {
        this.cijena = cijena;
    }

    public String getMjernaJedinica() {
        return mjernaJedinica;
    }

    public void setMjernaJedinica(String mjernaJedinica) {
        this.mjernaJedinica = mjernaJedinica;
    }

    public Integer getOcjena() {
        return ocjena;
    }

    public void setOcjena(Integer ocjena) {
        this.ocjena = ocjena;
    }

    @Override
    public String toString() {
        return "Product{" +
                "naziv='" + naziv + '\'' +
                ", cijena=" + cijena +
                ", mjernaJedinica='" + mjernaJedinica + '\'' +
                ", ocjena=" + ocjena +
                '}';
    }
}
