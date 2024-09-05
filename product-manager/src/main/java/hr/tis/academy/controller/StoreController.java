package hr.tis.academy.controller;

import hr.tis.academy.configuration.ExceptionHandlerController;
import hr.tis.academy.dto.StoreDto;
import hr.tis.academy.repository.exception.NoProductFoundException;
import hr.tis.academy.service.impl.StoreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/stores")
public class StoreController {

    private final StoreServiceImpl storeService;

    private final ExceptionHandlerController exceptionHandlerController;

    public StoreController(StoreServiceImpl storeService, ExceptionHandlerController exceptionHandlerController) {
        this.storeService = storeService;
        this.exceptionHandlerController = exceptionHandlerController;
    }

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
            throw new NoProductFoundException("Record with that id doesnt exist");

        }
        return new ResponseEntity<>(store, HttpStatus.OK);
    }

    @DeleteMapping("/{storeId}")
    public ResponseEntity<String> deleteStoreById(@PathVariable Long storeId) {
        StoreDto store = storeService.getStoreById(storeId);
        if (store == null) {
            return new ResponseEntity<>("Store not found", HttpStatus.NOT_FOUND);
        }
        storeService.deleteStoreById(storeId);
        return new ResponseEntity<>(String.format("Store with id %d deleted", storeId), HttpStatus.OK);
    }

    @PutMapping("/{storeId}")
    public ResponseEntity<String> updateStore(@PathVariable Long storeId, @RequestBody StoreDto storeDto) {
        boolean isUpdated = storeService.updateStore(storeId, storeDto);

        if (!isUpdated) {
            return new ResponseEntity<>("Store not found", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(String.format("Store with id %d updated successfully", storeId), HttpStatus.OK);
    }

    @PatchMapping("/{storeId}")
    public ResponseEntity<String> updateStoreNonNullValues(@PathVariable Long storeId, @RequestBody StoreDto storeDto) {
        boolean isUpdated = storeService.updateStoreNonNullValues(storeId, storeDto);

        if (!isUpdated) {
            return new ResponseEntity<>("Store not found", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(String.format("Store with id %d updated successfully", storeId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createStore(@RequestBody StoreDto storeDto) {
        storeService.addStore(storeDto);
        return new ResponseEntity<>("Store added", HttpStatus.CREATED);
    }
}
