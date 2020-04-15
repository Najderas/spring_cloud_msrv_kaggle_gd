package com.github.najderas.productservice.service;

import com.github.najderas.productservice.service.Product;

// import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
// import org.springframework.transaction.annotation.Transactional;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.client.RestTemplate;

// import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
// import java.util.HashMap;
// import java.util.Optional;
// import java.util.stream.Collectors;
// import java.util.concurrent.ThreadLocalRandom;

@Service
// @Transactional
public class ProductService {

    // private final RestTemplate restTemplate;
    private final InventoryServiceClient inventoryServiceClient;
    private final CatalogServiceClient catalogServiceClient;

    public ProductService(InventoryServiceClient inventoryServiceClient, CatalogServiceClient catalogServiceClient) {
        // this.restTemplate = restTemplate;
        this.inventoryServiceClient = inventoryServiceClient;
        this.catalogServiceClient = catalogServiceClient;
    }
    
    public Product findProductByCode(String code) {
        // short version
        if (inventoryServiceClient.findProductQuantity(code) > 0) {
            return catalogServiceClient.findProductByCode(code);
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
            if (inventoryServiceClient.findProductQuantity(prod.uniq_id) > 0) {
                filtered.add(prod);
            }
        }
        return filtered;
    }

    public List<Product> findProductsBySku(String sku) {

        List<Product> all_sku_products = catalogServiceClient.findProductBySku(sku);
        List<Product> filtered_sku_products = filterProductsByAvailability(all_sku_products);
        return filtered_sku_products;
    }


}