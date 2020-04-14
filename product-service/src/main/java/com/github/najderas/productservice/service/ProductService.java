package com.github.najderas.productservice.service;

import com.github.najderas.productservice.service.Product;

// import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
// import org.springframework.transaction.annotation.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.HashMap;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.concurrent.ThreadLocalRandom;

@Service
// @Transactional
public class ProductService {

    private final RestTemplate restTemplate;

    public ProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    
    private Product findProductInCatalogService(String code){
        Product prod = null;
        ResponseEntity<Product> itemResponseEntity =
                restTemplate.getForEntity("http://catalog-service/api/catalog/id/{code}",
                                            Product.class,
                                            code);
        if(itemResponseEntity.getStatusCode() == HttpStatus.OK) {
            prod = itemResponseEntity.getBody();
        }
        return prod;
    }

    private Integer findProductQuantityInInventoryService(String code){
        Integer quantity = null;
        ResponseEntity<Integer> itemResponseEntity =
                restTemplate.getForEntity("http://inventory-service/api/inventory/{code}",
                                            Integer.class,
                                            code);
        if(itemResponseEntity.getStatusCode() == HttpStatus.OK) {
            quantity = itemResponseEntity.getBody();
        } 
        return quantity;
    }


    public Product findProductByCode(String code) {
        Product prod = findProductInCatalogService(code);
        if (prod != null) {
            Integer quantity = findProductQuantityInInventoryService(code);
            if (quantity == 0) {
                prod = null;
            }
        }
        return prod;

        // return new Product(quantity.toString());

        // return Optional.ofNullable(matches.isEmpty() ? null : matches.get(0));
    }

    public List<Product> findProductsBySku(String sku) {
        return null;
        // return matches;
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