package hr.tis.academy.controller;

import hr.tis.academy.dto.StoreDto;
import hr.tis.academy.service.impl.StoreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/stores")
public class StoreController {

    @Autowired
    StoreServiceImpl storeService;

    @GetMapping
    public ResponseEntity<List<StoreDto>> getAll() {
        List<StoreDto> stores = storeService.getAllStores();

        if (stores.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(stores, HttpStatus.OK);
    }

    @GetMapping("/{storeId}")
    public ResponseEntity<StoreDto> getStoreById(@PathVariable Long storeId) {
        StoreDto store = storeService.getStoreById(storeId);
        if (store == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(store, HttpStatus.OK);
    }
}
