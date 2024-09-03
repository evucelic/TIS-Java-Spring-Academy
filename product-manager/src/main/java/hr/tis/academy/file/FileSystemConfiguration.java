package hr.tis.academy.file;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Path;
@Configuration
public class FileSystemConfiguration {

    @Value("${products.path}")
    private String productsPath;

    @Bean
    @Qualifier("productsFilesFolderPath")
    public Path productsFilesFolderPath(){
        return Path.of(productsPath);
    }


}
