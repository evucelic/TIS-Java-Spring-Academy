package hr.tis.academy;

import hr.tis.academy.repository.ProductRepository;
import hr.tis.academy.repository.ProductRepositoryInMemory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class App {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private ProductRepositoryInMemory productRepositoryInMemory;


    @Bean("myApplicationRunner")
    public ApplicationRunner applicationRunner() {
        return args -> {
            Arrays.stream(applicationContext.getBeanDefinitionNames()).forEach(System.out::println);

            System.out.printf("Products " + "record count: {" + productRepositoryInMemory.fetchProductsMetadataCount() + "}%n");
            //org.springframework.boot.autoconfigure.internalCachingMetadataReaderFactory
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
