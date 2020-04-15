package com.github.najderas.productservice.service;

import com.github.najderas.productservice.service.Product;

// import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
// import org.springframework.transaction.annotation.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.ArrayList;
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
    
    private Product findProductInCatalogServiceByCode(String code){
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

    private List<Product> findProductInCatalogServiceBySku(String sku){
        Product[] products_arr = null;
        ResponseEntity<Product[]> itemResponseEntity =
                restTemplate.getForEntity("http://catalog-service/api/catalog/sku/{sku}",
                                            Product[].class,
                                            sku);
        if (itemResponseEntity.getStatusCode() == HttpStatus.OK) {
            products_arr = itemResponseEntity.getBody();
        }
        List<Product> products = Arrays.asList(products_arr);

        return products;
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
        // short version
        if (findProductQuantityInInventoryService(code) > 0) {
            return findProductInCatalogServiceByCode(code);
        }
        return null;

        // long version
        // Product prod = findProductInCatalogServiceByCode(code);
        // if (prod != null) {
        //     Integer quantity = findProductQuantityInInventoryService(code);
        //     if (quantity == 0) {
        //         prod = null;
        //     }
        // }
        // return prod;
    }

    private List<Product> filterProductsByAvailability(List<Product> all_sku_products) {
        List<Product> filtered = new ArrayList<Product>();
        // Could be changed into "products.stream.filter(p->p.findQuantity()>0).collect"
        for (Product prod : all_sku_products) {
            if (findProductQuantityInInventoryService(prod.uniq_id) > 0) {
                filtered.add(prod);
            }
        }
        return filtered;
    }

    public List<Product> findProductsBySku(String sku) {

        List<Product> all_sku_products = findProductInCatalogServiceBySku(sku);
        List<Product> filtered_sku_products = filterProductsByAvailability(all_sku_products);
        return filtered_sku_products;
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