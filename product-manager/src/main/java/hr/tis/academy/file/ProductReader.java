package hr.tis.academy.file;

import hr.tis.academy.model.Product;
import hr.tis.academy.model.ProductsMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductReader {

    public final Path productsFilesFolderPath;

    @Autowired
    public ProductReader(@Qualifier("productsFilesFolderPath") Path productsFilesFolderPath) {
        this.productsFilesFolderPath = productsFilesFolderPath;
    }

    public ProductsMetadata read(String fileName) {
        List<Product> products = new ArrayList<>();
        String parsedName = fileName.split("\\.txt")[0];
        String[] parsedNameList = parsedName.split("_");

        Long id = Long.valueOf(parsedNameList[0]);
        LocalDateTime localDateTime = LocalDateTime.parse(parsedNameList[1].replace("$",":"));
        String naslov = parsedNameList[2];


        try (BufferedReader reader = Files.newBufferedReader(
                Paths.get(productsFilesFolderPath.resolve(fileName).toString()))) {
            String line;

            while ((line = reader.readLine()) != null) {

                String ime = line.substring(0,99).strip();
                BigDecimal cijena = BigDecimal.valueOf(Long.parseLong(line.substring(100,110).strip()));
                String mjernaJedinica = line.substring(110,120).strip();
                Integer ocjena = Integer.valueOf(line.substring(120,121));

                Product proizvod = new Product(ime, cijena, mjernaJedinica, ocjena);
                products.add(proizvod);
            }

        } catch (IOException e) {
            throw new RuntimeException("Error reading the file", e);
        }

        return new ProductsMetadata(id, localDateTime,naslov, products);

    }
}
