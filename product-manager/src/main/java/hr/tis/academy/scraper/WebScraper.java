package hr.tis.academy.scraper;

import hr.tis.academy.model.Product;
import hr.tis.academy.model.ProductsMetadata;
import hr.tis.academy.repository.ProductRepositoryDB;
import org.jsoup.*;
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
        for (int i = 1; i<= numberOfPagesForScraping; i++) {
            if (i==1) {
                doc = Jsoup.connect(urlKonzum).get();
                products = doc.select("div.product-wrapper");
            } else {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(urlKonzum);
                stringBuilder.append("?page=");
                stringBuilder.append(i);
                stringBuilder.append("&per_page=25&sort%5B%5D=");
                doc = Jsoup.connect(stringBuilder.toString()).get();
                products = doc.select("div.product-wrapper");
            }

            for (Element product : products) {
                String naslov_product = product.select(".product-default__title").text();

                Elements price = product.select(".product-default__footer");
                BigDecimal cijena = new BigDecimal(price.select(".price--kn").text() + "." +
                        price.select(".price--li").text());
                String jedinica = price.select(".price--c").text();
                Product product1 = new Product(naslov_product, cijena, jedinica);
                produkti.add(product1);
            }
        }
        naslov = doc.title();
        ProductRepositoryDB productRepositoryDB = new ProductRepositoryDB();
        ProductsMetadata productsMetadata =
                new ProductsMetadata((long) (productRepositoryDB.fetchProductsMetadataCount() + 1),
                LocalDateTime.now(),
                naslov,
                produkti);
        return productsMetadata;
    }

    public static void main(String[] args) throws IOException {
        WebScraper webScraper = new WebScraper();
        System.out.println((webScraper.fetchProducts()));
    }
}
