package com.github.najderas.productservice.service;

import com.github.najderas.productservice.service.Product;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

// import java.util.Optional;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


@Service
public class CatalogServiceClient {

    private final RestTemplate restTemplate;
    private static final String CATALOG_API_PATH = "http://catalog-service/api/catalog/";

    @Autowired
    public CatalogServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @HystrixCommand(fallbackMethod = "findProductByCodeFallback")
    public Product findProductByCode(String code){
        Product prod = null;
        ResponseEntity<Product> itemResponseEntity =
                restTemplate.getForEntity("http://catalog-service/api/catalog/id/{code}",
                                            Product.class,
                                            code);
        if(itemResponseEntity.getStatusCode() == HttpStatus.OK) {
            prod = itemResponseEntity.getBody();
        }

         /*
         //Simulate Delay
         try {
             TimeUnit.SECONDS.sleep(5);
         } catch (InterruptedException e) {
             e.printStackTrace();
         }
         */

        return prod;
    }

    @HystrixCommand(fallbackMethod = "findProductBySkuFallback")
    public List<Product> findProductBySku(String sku){
        Product[] products_arr = null;
        ResponseEntity<Product[]> itemResponseEntity =
                restTemplate.getForEntity("http://catalog-service/api/catalog/sku/{sku}",
                                            Product[].class,
                                            sku);
        if (itemResponseEntity.getStatusCode() == HttpStatus.OK) {
            products_arr = itemResponseEntity.getBody();
        }
        List<Product> products = Arrays.asList(products_arr);

         /*
         //Simulate Delay
         try {
             TimeUnit.SECONDS.sleep(5);
         } catch (InterruptedException e) {
             e.printStackTrace();
         }
         */

        return products;
    }

    // Fallbacks
    @SuppressWarnings("unused")
    public Product findProductByCodeFallback(String code){
        throw new ServiceUnavailableException("Inventory service unavailable! Cannot procede with request.");
    }

    @SuppressWarnings("unused")
    public List<Product> findProductBySkuFallback(String sku){
        throw new ServiceUnavailableException("Inventory service unavailable! Cannot procede with request.");
    }


}