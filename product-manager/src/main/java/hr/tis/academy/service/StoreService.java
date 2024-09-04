package hr.tis.academy.service;

import hr.tis.academy.dto.StoreDto;

import java.util.List;

public interface StoreService {
    void init();

    List<StoreDto> getAllStores();

    StoreDto getStoreById(Long id);

    void addStore(StoreDto store);
    void deleteStoreById(Long id);
    boolean updateStore(Long id, StoreDto store);
}
