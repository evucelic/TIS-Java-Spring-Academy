package hr.tis.academy.repository;

import hr.tis.academy.db.Database;
import hr.tis.academy.model.Product;
import hr.tis.academy.model.ProductsMetadata;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ProductRepositoryDB implements ProductRepository{

    @Override
    public Long insertProducts(ProductsMetadata productsMetadata) {
        String recordSQL = "INSERT INTO PRODUCTS_METADATA (DATUM_VRIJEME_KREIRANJA, NASLOV) VALUES (?, ?)";
        String productSQL = "INSERT INTO PRODUCTS (NAME, CIJENA, JEDINICA, OCJENA, METADATA_ID) VALUES (?, ?, ?, ?, ?)";
        long recordId;
        try (Connection connection = Database.getInstance().getConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement recordStmt = connection.prepareStatement(recordSQL,
                    Statement.RETURN_GENERATED_KEYS)) {
                recordStmt.setDate(1, Date.valueOf(productsMetadata.getDatumVrijemeKreiranja().toLocalDate()));
                recordStmt.setString(2, productsMetadata.getNaslov());
                recordStmt.executeUpdate();
                try (ResultSet generatedKeys = recordStmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        recordId = generatedKeys.getLong(1);
                        try (PreparedStatement productStmt = connection.prepareStatement(productSQL)) {
                            for (Product product : productsMetadata.getPopisProizvoda()) {
                                productStmt.setString(1, product.getNaziv());
                                productStmt.setBigDecimal(2, product.getCijena());
                                productStmt.setString(3, product.getMjernaJedinica());
                                productStmt.setInt(4, product.getOcjena());
                                productStmt.setLong(5, recordId);
                                productStmt.addBatch();
                            }
                            productStmt.executeBatch();
                        }
                    } else {
                        throw new RuntimeException("Creating PRODUCTS_METADATA failed, no ID obtained.");
                    }
                }
            }
            connection.commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return recordId;

    }

    @Override
    public BigDecimal fetchSumOfPrices(LocalDate createdDate) {
        return null;
    }

    @Override
    public BigDecimal fetchSumOfPrices(Long id) {
        return null;
    }

    @Override
    public ProductsMetadata fetchProductsMetadata(LocalDate createdDate) {
        return null;
    }

    @Override
    public ProductsMetadata fetchProductsMetadata(Long id) {
        String querySQL = "SELECT * FROM PRODUCTS_METADATA WHERE ID = ?";
        String querySQL2 = "SELECT * FROM PRODUCTS WHERE METADATA_ID = ?";



        try (Connection connection = Database.getInstance().getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(querySQL)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                ProductsMetadata productsMetadata =
                        new ProductsMetadata(
                                resultSet.getLong("ID"),
                                resultSet.getTimestamp("DATUM_VRIJEME_KREIRANJA").toLocalDateTime(),
                                resultSet.getString("NASLOV"),
                                null);
                return productsMetadata;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Integer fetchProductsMetadataCount() {
        return null;
    }

    public static void main(String[] args) {
        List<Product> lista = new ArrayList<>();
        Product proizvod1 = new Product("mlijeko", new BigDecimal("5"), "EUR");
        Product proizvod2 = new Product("sir", new BigDecimal("10"), "EUR", 5);
        Product proizvod3 = new Product("kava", new BigDecimal("1"), "EUR", 3);
        lista.add(proizvod1);
        lista.add(proizvod2);
        lista.add(proizvod3);
        ProductsMetadata productsMetadata = new ProductsMetadata(1L, LocalDateTime.now(), "productmetadata",lista);


        ProductRepositoryDB productRepositoryDB = new ProductRepositoryDB();
        productRepositoryDB.insertProducts(productsMetadata);

        System.out.println(productRepositoryDB.fetchProductsMetadata(1L));
    }
}
