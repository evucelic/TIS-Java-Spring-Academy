package hr.tis.academy.model;

import java.math.BigDecimal;

public class Product {
    private String naziv;
    private BigDecimal cijena;
    private String mjernaJedinica;
    private Integer ocjena = 0;

    public Product(String naziv, BigDecimal cijena, String mjernaJedinica, Integer ocjena) {
        this.naziv = naziv;
        this.cijena = cijena;
        this.mjernaJedinica = mjernaJedinica;
        this.ocjena = ocjena;
    }

    public Product(String naziv, BigDecimal cijena, String mjernaJedinica) {
        this.naziv = naziv;
        this.cijena = cijena;
        this.mjernaJedinica = mjernaJedinica;
    }

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
