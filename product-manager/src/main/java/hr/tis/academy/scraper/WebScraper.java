package hr.tis.academy.scraper;

import hr.tis.academy.model.Product;
import hr.tis.academy.model.ProductsMetadata;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class WebScraper {

    @Value("${konzum.url}")
    private String urlKonzum;

    @Value("${number.of.pages.for.scraping}")
    private Integer numberOfPagesForScraping;

    public ProductsMetadata fetchProducts() throws IOException {

        Elements products = null;
        String naslov = null;
        Document doc = null;
        List<Product> produkti = new ArrayList<>();

        // Create ProductsMetadata object
        ProductsMetadata productsMetadata = new ProductsMetadata();
        productsMetadata.setDatumVrijemeKreiranja(LocalDateTime.now());

        for (int i = 1; i <= numberOfPagesForScraping; i++) {
            if (i == 1) {
                doc = Jsoup.connect(urlKonzum).get();
                products = doc.select("div.product-wrapper");
            } else {
                String stringBuilder = urlKonzum +
                        "?page=" +
                        i +
                        "&per_page=25&sort%5B%5D=";
                doc = Jsoup.connect(stringBuilder).get();
                products = doc.select("div.product-wrapper");
            }

            for (Element product : products) {
                String naslov_product = product.select(".product-default__title").text();

                Elements price = product.select(".product-default__footer");
                BigDecimal cijena = new BigDecimal(price.select(".price--kn").text() + "." +
                        price.select(".price--li").text());
                String jedinica = price.select(".price--c").text();

                // Now, create a new Product object and associate it with ProductsMetadata
                Product product1 = new Product(naslov_product, cijena, jedinica, productsMetadata);
                produkti.add(product1);
            }
        }

        assert doc != null;
        naslov = doc.title();
        productsMetadata.setNaslov(naslov);
        productsMetadata.setPopisProizvoda(produkti); // Set the list of products

        return productsMetadata;
    }
}
