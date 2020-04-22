package com.github.najderas.catalogservice.web;

import com.github.najderas.catalogservice.service.Product;
import com.github.najderas.catalogservice.service.CatalogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/catalog")
public class CatalogController {

    private final CatalogService catalogService;

    @Autowired
    public CatalogController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @GetMapping("")
    public List<Product> allProducts() {
        return catalogService.allProducts();
    }

    @GetMapping("/id/{code}")
    public Product productByCode(@PathVariable String code) {
        return catalogService.findProductByCode(code)
                .orElseThrow(() -> new ProductNotFoundException("Product with code ["+code+"] doesn't exist"));
    }
    @GetMapping("/sku/{sku}")
    public List<Product> productsBySku(@PathVariable String sku) {
        return catalogService.findProductsBySku(sku);
    }

}