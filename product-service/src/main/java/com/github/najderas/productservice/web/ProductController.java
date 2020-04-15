package com.github.najderas.productservice.web;

import com.github.najderas.productservice.service.Product;
import com.github.najderas.productservice.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    
    @GetMapping("/id/{code}")
    public Product productByCode(@PathVariable String code) {
        return productService.findProductByCode(code);
    }
    
    @GetMapping("/sku/{sku}")
    public List<Product> productsBySku(@PathVariable String sku) {
        return productService.findProductsBySku(sku);
        // return catalogService.findProductsBySku(sku)
        //         .orElseThrow(() -> new ProductNotFoundException("Product with sku ["+sku+"] doesn't exist"));
    }
    
    

}