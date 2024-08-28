package hr.tis.akademija.file;

import hr.tis.akademija.model.Product;
import hr.tis.akademija.model.ProductsMetadata;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ProductReader {

    public static ProductsMetadata read(String fileName) {
        List<Product> products = new ArrayList<>();
        String parsedName = fileName.split("\\.txt")[0];
        String[] parsedNameList = parsedName.split("_");

        Long id = Long.valueOf(parsedNameList[0]);
        LocalDateTime localDateTime = LocalDateTime.parse(parsedNameList[1].replace("$",":"));
        String naslov = parsedNameList[2];


        try (BufferedReader reader = Files.newBufferedReader(
                Paths.get(FileSystemConfiguration.PRODUCTS_FILES_FOLDER_PATH.resolve(fileName).toString()))) {
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

    public static void main(String[] args) throws IOException {

        read("1_2024-08-28T14$33$02.129050_productmetadata.txt");

    }
}
