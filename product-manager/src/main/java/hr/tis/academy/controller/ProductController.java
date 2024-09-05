package hr.tis.academy.controller;

import hr.tis.academy.dto.ProductsMetadataDto;
import hr.tis.academy.mapper.ProductsMetadataMapper;
import hr.tis.academy.model.Product;
import hr.tis.academy.model.ProductsMetadata;
import hr.tis.academy.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping
    public ResponseEntity<ProductsMetadataDto> getProductsMetadataDto(){
        return new ResponseEntity<>(productService.getProductsMetadata(), HttpStatus.OK);
    }

    @GetMapping("save")
    public void saveProductsMetadataDto() throws IOException {
        productService.save();
    }
}
