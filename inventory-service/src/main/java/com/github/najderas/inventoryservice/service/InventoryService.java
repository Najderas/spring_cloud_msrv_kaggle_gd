package com.github.najderas.inventoryservice.service;

import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

@Service
// @Transactional
public class InventoryService {

    // private List<Product> products;
    HashMap<String, Integer> availability;

    public InventoryService() {
        availability = new HashMap<String, Integer>();
    }
    
    private Integer generateAvailability(String code) {
        int randomNum = ThreadLocalRandom.current().nextInt(0, 3001);
        // simulate products shortages
        if (Integer.parseInt(code) < 8) {
            randomNum = 0;
        }
        availability.put(code, randomNum);
        return randomNum;
    }

    public Integer availabilityByCode(String code) {
        Integer result = availability.get(code);
        if (result == null) {
            result = generateAvailability(code);
        }
        return result;
    }
}