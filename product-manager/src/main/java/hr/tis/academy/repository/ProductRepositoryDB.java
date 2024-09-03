package hr.tis.academy.repository;

import hr.tis.academy.db.Database;
import hr.tis.academy.db.DatabaseException;
import hr.tis.academy.model.Product;
import hr.tis.academy.model.ProductsMetadata;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@Profile("DB")
public class ProductRepositoryDB implements ProductRepository {

    public static void main(String[] args) {
        List<Product> lista = new ArrayList<>();
        Product proizvod1 = new Product("mlijeko", new BigDecimal("5"), "EUR");
        Product proizvod2 = new Product("sir", new BigDecimal("10"), "EUR", 5);
        Product proizvod3 = new Product("kava", new BigDecimal("1"), "EUR", 3);
        lista.add(proizvod1);
        lista.add(proizvod2);
        lista.add(proizvod3);
        ProductsMetadata productsMetadata = new ProductsMetadata(1L, LocalDateTime.now(), "productmetadata", lista);


        ProductRepositoryDB productRepositoryDB = new ProductRepositoryDB();
        productRepositoryDB.insertProducts(productsMetadata);

        System.out.println(productRepositoryDB.fetchProductsMetadata(LocalDate.now()));
        System.out.println(productRepositoryDB.fetchProductsMetadataCount());
    }

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
        ProductsMetadata product = fetchProductsMetadata(createdDate);

        return calculateSumOfPrices(product.getPopisProizvoda());
    }

    @Override
    public BigDecimal fetchSumOfPrices(Long id) {
        ProductsMetadata productsMetadata = fetchProductsMetadata(id);

        return calculateSumOfPrices(productsMetadata.getPopisProizvoda());
    }

    @Override
    public ProductsMetadata fetchProductsMetadata(LocalDate createdDate) {
        String querySQL = "SELECT * FROM PRODUCTS_METADATA WHERE DATUM_VRIJEME_KREIRANJA BETWEEN ? AND ?";


        try (Connection connection = Database.getInstance().getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(querySQL)
        ) {
            preparedStatement.setTimestamp(1, Timestamp.valueOf(createdDate.atStartOfDay()));
            preparedStatement.setTimestamp(2, Timestamp.valueOf(createdDate.atTime(23, 59, 59)));

            ResultSet resultSet = preparedStatement.executeQuery();


            if (resultSet.next()) {

                ProductsMetadata productsMetadata =
                        new ProductsMetadata(
                                resultSet.getLong("ID"),
                                resultSet.getTimestamp("DATUM_VRIJEME_KREIRANJA").toLocalDateTime(),
                                resultSet.getString("NASLOV"),
                                findProducts(resultSet.getLong("ID"))
                        );
                return productsMetadata;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public ProductsMetadata fetchProductsMetadata(Long id) {
        String querySQL = "SELECT * FROM PRODUCTS_METADATA WHERE ID = ?";


        try (Connection connection = Database.getInstance().getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(querySQL)
        ) {
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            List<Product> products = findProducts(id);

            if (resultSet.next()) {

                ProductsMetadata productsMetadata =
                        new ProductsMetadata(
                                resultSet.getLong("ID"),
                                resultSet.getTimestamp("DATUM_VRIJEME_KREIRANJA").toLocalDateTime(),
                                resultSet.getString("NASLOV"),
                                products);
                return productsMetadata;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Integer fetchProductsMetadataCount() {
        String querySQL = "SELECT COUNT(DISTINCT ID) FROM PRODUCTS_METADATA";

        try (Connection connection = Database.getInstance().getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(querySQL)
        ){
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    private List<Product> findProducts(Long id) {

        String querySQL2 = "SELECT * FROM PRODUCTS WHERE METADATA_ID = ?";
        List<Product> products = new ArrayList<>();

        try (Connection connection = Database.getInstance().getConnection();
             PreparedStatement preparedStatement2 =
                     connection.prepareStatement(querySQL2)
        ) {
            preparedStatement2.setLong(1, id);

            ResultSet resultSet2 = preparedStatement2.executeQuery();


            while (resultSet2.next()) {
                products.add(new Product(resultSet2.getString("NAME"),
                        resultSet2.getBigDecimal("CIJENA"),
                        resultSet2.getString("JEDINICA"),
                        resultSet2.getInt("OCJENA")));
            }

            return products;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> fetchAll() {
        String querySQL = "SELECT * FROM KORISNICI";
        List<String> users = new ArrayList<>();
        try (Connection connection = Database.getInstance().getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(querySQL);
            while (resultSet.next()) {
                Long id = resultSet.getLong("ID");
                String name = resultSet.getString("NAME");
                users.add(String.format("%s %s", id, name));
            }
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
        return users;
    }
}
