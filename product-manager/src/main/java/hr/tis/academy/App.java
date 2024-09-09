package hr.tis.academy;

import hr.tis.academy.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class App {

    @Autowired
    private ApplicationContext applicationContext;


    @Autowired
    private ProductsMetadataRepository productsMetadataRepository;

    @Autowired
    private ProductRepositoryJPA productsRepositoryJPA;

    /*public App(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Autowired
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }*/

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean("myApplicationRunner")
    public ApplicationRunner applicationRunner() {
        return args -> {
            productsRepositoryJPA.fetchByNazivAndOcjena("",0);
            //Arrays.stream(applicationContext.getBeanDefinitionNames()).forEach(System.out::println);
            //ProductRepositoryInMemory productRepositoryInMemory =
            //      (ProductRepositoryInMemory) applicationContext.getBean("myProductRepositoryInMemory");
            //System.out.printf("Products record count: %d\n", productRepositoryInMemory.fetchProductsMetadataCount());
            //org.springframework.boot.autoconfigure.internalCachingMetadataReaderFactory
            /*if (productRepository instanceof ProductRepositoryInMemory) {
                System.out.println("In Memory");
            } else if (productRepository instanceof ProductRepositoryFile) {
                System.out.println("File");
            } else if (productRepository instanceof ProductRepositoryDB) {
                System.out.println("DB");
            }*/
            productsMetadataRepository.findByNaslovAndDatumVrijemeKreiranja("asd", LocalDateTime.now());
            productsMetadataRepository.fetchByTitleAndCreatedTimeJPQL("asd", LocalDateTime.MAX);
            productsMetadataRepository.fetchByTitleAndCreatedTimeNative("asd", LocalDateTime.MIN);
        };
    }
}
