package hr.tis.academy.file;

import hr.tis.academy.model.Product;
import hr.tis.academy.model.ProductsMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductWriter {

    public final Path productsFilesFolderPath;

    @Autowired
    public ProductWriter(@Qualifier("productsFilesFolderPath") Path productsFilesFolderPath) {
        this.productsFilesFolderPath = productsFilesFolderPath;
    }

    public void writeProducts(ProductsMetadata productsMetadata){
        String fileName = String.format("%s_%s_%s.txt", productsMetadata.getId(),
                productsMetadata.getDatumVrijemeKreiranja(), productsMetadata.getNaslov()).replace(":", "$");
        File theDir = new File(productsFilesFolderPath.resolve(fileName).toAbsolutePath().getParent().toString());
        if (!theDir.exists()) {
            theDir.mkdirs();
        }

        try (BufferedWriter writer = Files.newBufferedWriter(
                productsFilesFolderPath.resolve(fileName))) {
            // TODO pisanje proizvoda u datoteku
            for (Product product : productsMetadata.getPopisProizvoda()) {
                String string = String.format("%s%s%s%d", padRight(product.getNaziv(), 100),
                        padLeft(product.getCijena().toString(), 10),
                        padRight(product.getMjernaJedinica(), 10),
                        product.getOcjena()
                );
                writer.write(string + "\n");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static String padRight(String s, int n) {
        return String.format("%-" + n + "s", s);
    }

    public static String padLeft(String s, int n) {
        return String.format("%" + n + "s", s);
    }

    public static void main(String[] args) {
        List<Product> lista = new ArrayList<>();
        Product proizvod1 = new Product("mlijeko", new BigDecimal("5"), "EUR");
        Product proizvod2 = new Product("sir", new BigDecimal("10"), "EUR", 5);
        lista.add(proizvod1);
        lista.add(proizvod2);
        ProductsMetadata productsMetadata = new ProductsMetadata(1L, LocalDateTime.now(), "productmetadata",lista);
    }

}
