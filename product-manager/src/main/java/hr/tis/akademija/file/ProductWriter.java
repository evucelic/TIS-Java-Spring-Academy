package hr.tis.akademija.file;

import hr.tis.akademija.model.Product;
import hr.tis.akademija.model.ProductsMetadata;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static hr.tis.akademija.file.FileSystemConfiguration.PRODUCTS_FILES_FOLDER_PATH;

public class ProductWriter {

    public static void writeProducts(ProductsMetadata productsMetadata){
        String fileName = String.format("%s_%s_%s.txt", productsMetadata.getId(),
                productsMetadata.getDatumVrijemeKreiranja(), productsMetadata.getNaslov()).replace(":", "$");
        try (BufferedWriter writer = Files.newBufferedWriter(
                PRODUCTS_FILES_FOLDER_PATH.resolve(fileName))) {
            /* TODO pisanje proizvoda u datoteku
            for (Product product : productsMetadata.getPopisProizvoda()) {
                String string = String.format("%s%d%s%d", padRight(product.getNaziv(), 100),
                        padLeft(product.getCijena().toString(), 10),
                        padRight(product.getMjernaJedinica(), 10),
                        product.getOcjena()
                );
                System.out.println(string);
                writer.write(string);
            }*/
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

        writeProducts(productsMetadata);

    }

}
