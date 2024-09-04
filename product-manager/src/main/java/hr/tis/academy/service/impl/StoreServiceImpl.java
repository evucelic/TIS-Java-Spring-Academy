package hr.tis.academy.service.impl;

import hr.tis.academy.dto.AddressDto;
import hr.tis.academy.dto.StoreDto;
import hr.tis.academy.dto.WorkingDayDto;
import hr.tis.academy.service.StoreService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StoreServiceImpl implements StoreService {

    private final Map<Long, StoreDto> storeMap = new HashMap<>();

    @PostConstruct
    public void init(){
        // primjer
        AddressDto address1 = new AddressDto("New York", "USA", "5th Avenue", "101");
        AddressDto address2 = new AddressDto("Los Angeles", "USA", "Sunset Boulevard", "202");

        List<WorkingDayDto> workingDays = new ArrayList<>();
        workingDays.add(new WorkingDayDto("MONDAY", "09:00:00", "17:00:00"));
        workingDays.add(new WorkingDayDto("TUESDAY", "09:00:00", "17:00:00"));

        storeMap.put(1L, new StoreDto("7-eleven", address1, "01-234-567-8900", "info@seveneleven.com", workingDays));
        storeMap.put(2L, new StoreDto("Walmart", address2, "01-987-654-3210", "info@walmart.com", workingDays));
    }

    @Override
    public List<StoreDto> getAllStores() {
        return new ArrayList<>(storeMap.values());
    }

    @Override
    public StoreDto getStoreById(Long id) {
        return storeMap.get(id);
    }


}
