package hr.tis.academy.controller;

import hr.tis.academy.dto.ProductsMetadataDto;
import hr.tis.academy.mapper.ProductsMetadataMapper;
import hr.tis.academy.model.Product;
import hr.tis.academy.model.ProductsMetadata;
import hr.tis.academy.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping
    public ResponseEntity<ProductsMetadataDto> getProductsMetadataDto(){
        return new ResponseEntity<>(productService.getProductsMetadata(), HttpStatus.OK);
    }

    @PutMapping("save")
    public void saveProductsMetadataDto() throws IOException {
        productService.save();
    }

    @GetMapping("date")
    public ResponseEntity<ProductsMetadataDto> getProductsMetadataDtoByDate
            (@RequestParam String year,
             @RequestParam String month,
             @RequestParam String day) {
        String dateString = year + "-" + month + "-" + day;
        LocalDate localDate = LocalDate.parse(dateString);
        return new ResponseEntity<>(productService.getByDate(localDate), HttpStatus.OK);
    }
}
