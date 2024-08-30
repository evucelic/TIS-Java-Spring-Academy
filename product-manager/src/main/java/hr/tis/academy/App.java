package hr.tis.academy;

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

    @Bean
    public ApplicationRunner applicationRunner() {
        return args -> {
            Arrays.stream(applicationContext.getBeanDefinitionNames()).forEach(System.out::println);
            //org.springframework.boot.autoconfigure.internalCachingMetadataReaderFactory
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
