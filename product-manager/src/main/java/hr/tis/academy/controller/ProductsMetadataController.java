package hr.tis.academy.controller;

import hr.tis.academy.dto.ProductsMetadataDto;
import hr.tis.academy.mapper.ProductsMetadataMapper;
import hr.tis.academy.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;


@RestController
@RequestMapping("/products")
public class ProductsMetadataController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductsMetadataMapper productsMetadataMapper;

    @GetMapping
    public ResponseEntity<ProductsMetadataDto> getProductsMetadata() {
        return new ResponseEntity<>(productService.getProductsMetadata(), HttpStatus.OK);
    }

    @PutMapping("/save")
    public void save() throws IOException {
        productService.save();
    }

    @GetMapping("/date")
    public ResponseEntity<ProductsMetadataDto> getProductsMetadataDate(@RequestParam String year,
                                                                       @RequestParam String month,
                                                                       @RequestParam String day) throws IOException {
        String dateString = year + "-" + month + "-" + day;
        return  new ResponseEntity<>(productService.getByDate(LocalDate.parse(dateString)), HttpStatus.OK);
    }




}
