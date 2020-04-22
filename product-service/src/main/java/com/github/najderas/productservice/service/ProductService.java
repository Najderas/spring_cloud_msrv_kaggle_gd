package com.github.najderas.productservice.service;

import com.github.najderas.productservice.service.Product;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
// @Transactional
public class ProductService {

    private final InventoryServiceClient inventoryServiceClient;
    private final CatalogServiceClient catalogServiceClient;

    public ProductService(InventoryServiceClient inventoryServiceClient, CatalogServiceClient catalogServiceClient) {
        this.inventoryServiceClient = inventoryServiceClient;
        this.catalogServiceClient = catalogServiceClient;
    }
    
    public Product findProductByCode(String code) {
        // TODO: I should throw ServiceUnavailableException here, outside hystrix to make it always work properly... :(
        if (inventoryServiceClient.findProductQuantity(code) > 0) {
            return catalogServiceClient.findProductByCode(code);
        }
        return null;
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