package com.github.najderas.productservice.service;

// import com.github.najderas.productservice.service.Product;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

// import java.util.Optional;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;


@Service
public class InventoryServiceClient {

    private final RestTemplate restTemplate;
    private static final String INVENTORY_API_PATH = "http://inventory-service/api/inventory/";

    @Autowired
    public InventoryServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @HystrixCommand(fallbackMethod = "findProductQuantityFallback")
    public Integer findProductQuantity(String code){
        Integer quantity = null;
        ResponseEntity<Integer> itemResponseEntity =
                restTemplate.getForEntity("http://inventory-service/api/inventory/{code}",
                                            Integer.class,
                                            code);
        if(itemResponseEntity.getStatusCode() == HttpStatus.OK) {
            quantity = itemResponseEntity.getBody();
        }

         /*
         //Simulate Delay
         try {
             TimeUnit.SECONDS.sleep(5);
         } catch (InterruptedException e) {
             e.printStackTrace();
         }
         */

        return quantity;
    }

    public Integer findProductQuantityFallback(String _code) {
        throw new ServiceUnavailableException("Inventory service unavailable! Cannot procede with request.");
    }

}