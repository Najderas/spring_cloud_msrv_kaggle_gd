package com.github.najderas.inventoryservice.service;

// import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
// import org.springframework.transaction.annotation.Transactional;
// import java.util.List;
import java.util.HashMap;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.concurrent.ThreadLocalRandom;

@Service
// @Transactional
public class InventoryService {

    // private List<Product> products;
    HashMap<String, Integer> availability;

    public InventoryService() {
        availability = new HashMap<String, Integer>();
    }
    

    public Integer availabilityByCode(String code) {
        Integer result = availability.get(code);
        if (result == null) {
            int randomNum = ThreadLocalRandom.current().nextInt(0, 3001);
            result = randomNum;
            availability.put(code, result);
        }
        return result;
    }

    // public Optional<Product> findProductByCode(String code) {
    //     // return Optional.ofNullable(products.get(0));
    //     List<Product> matches = products.stream().filter(p -> p.uniq_id.equals(code)).collect(Collectors.toList());
    //     // if (matches.isEmpty()) {
    //     //     return Optional.empty();
    //     // } else {
    //     //     return Optional.ofNullable(matches.get(0));
    //     // }

    //     return Optional.ofNullable(matches.isEmpty() ? null : matches.get(0));
    // }

    // public List<Product> findProductsBySku(String sku) {
    //     List<Product> matches = products.stream().filter(p -> p.sku.equals(sku)).collect(Collectors.toList());
        
    //     return matches;
    // }

}