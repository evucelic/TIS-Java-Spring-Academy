package hr.tis.academy;

import hr.tis.academy.repository.ProductRepository;
import hr.tis.academy.repository.ProductRepositoryDB;
import hr.tis.academy.repository.ProductRepositoryFile;
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
    private ProductRepository productRepository;

    /*public App(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Autowired
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }*/

    @Bean("myApplicationRunner")
    public ApplicationRunner applicationRunner() {
        return args -> {
            System.out.printf("Products record count: %d\n", productRepository.fetchProductsMetadataCount());
            //Arrays.stream(applicationContext.getBeanDefinitionNames()).forEach(System.out::println);
            //ProductRepositoryInMemory productRepositoryInMemory =
            //      (ProductRepositoryInMemory) applicationContext.getBean("myProductRepositoryInMemory");
            //System.out.printf("Products record count: %d\n", productRepositoryInMemory.fetchProductsMetadataCount());
            //org.springframework.boot.autoconfigure.internalCachingMetadataReaderFactory
            if (productRepository instanceof ProductRepositoryInMemory){
                System.out.println("In Memory");
            } else if (productRepository instanceof ProductRepositoryFile){
                System.out.println("File");
            } else if (productRepository instanceof ProductRepositoryDB){
                System.out.println("DB");
            }
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
