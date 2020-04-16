package com.github.najderas.catalogservice.service;

import com.github.najderas.catalogservice.service.Product;
// import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
// import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
// @Transactional
public class CatalogService {

    private List<Product> products;

    public CatalogService() {
        initProductsList();
    }
    

    public List<Product> allProducts() {
        return products;
    }

    public Optional<Product> findProductByCode(String code) {

        List<Product> matches = products.stream().filter(p -> p.uniq_id.equals(code)).collect(Collectors.toList());

        return Optional.ofNullable(matches.isEmpty() ? null : matches.get(0));
    }

    public List<Product> findProductsBySku(String sku) {
        
        List<Product> matches = products.stream().filter(p -> p.sku.equals(sku)).collect(Collectors.toList());
        
        return matches;
    }

    private void initProductsList() {

        products = new ArrayList();
        // new Product(uniq_id, "", "", "", "", "", "", "", "", "", "", "", "", "");
        // new Product(uniq_id, sku, name_title, description, list_price, sale_price, category, category_tree, avg_product_rating, "", "", "", tot_no_reviews, "");
        products.add(new Product("1", "234", "abcdef", "", "13", "", "toot", "", "", "", "", "", "", ""));
        products.add(new Product("2", "234", "wwwwe", "", "123", "", "toot", "", "", "", "", "", "", ""));
        products.add(new Product("3", "234", "tttweww", "", "3", "", "toot", "", "", "", "", "", "", ""));
        products.add(new Product("4", "345", "omglmao", "", "13", "", "yupi", "", "", "", "", "", "", ""));
        products.add(new Product("5", "345", "omgnfao", "", "31", "", "toot", "", "", "", "", "", "", ""));
        products.add(new Product("6", "456", "barbarbaz", "", "13", "", "toot", "", "", "", "", "", "", ""));
        products.add(new Product("7", "567", "foo", "", "6", "", "boo", "", "", "", "", "", "", ""));
        products.add(new Product("8", "567", "bar", "", "7", "", "boo", "", "", "", "", "", "", ""));
        products.add(new Product("9", "567", "baz", "", "8", "", "boo", "", "", "", "", "", "", ""));
        products.add(new Product("10", "567", "doo", "", "11", "", "boom", "", "", "", "", "", "", ""));
        products.add(new Product("11", "234", "abcdef", "", "18", "", "toot", "", "", "", "", "", "", ""));
        products.add(new Product("12", "234", "wwwwe", "", "95", "", "toot", "", "", "", "", "", "", ""));
        products.add(new Product("13", "234", "tttweww", "", "23", "", "toot", "", "", "", "", "", "", ""));
        products.add(new Product("14", "345", "omglmao", "", "23", "", "yupi", "", "", "", "", "", "", ""));
        products.add(new Product("15", "345", "omgnfao", "", "231", "", "toot", "", "", "", "", "", "", ""));
        products.add(new Product("16", "456", "barbarbaz", "", "17", "", "toot", "", "", "", "", "", "", ""));
        products.add(new Product("17", "567", "foo", "", "6", "", "boo", "", "", "", "", "", "", ""));
        products.add(new Product("18", "567", "bar", "", "7", "", "boo", "", "", "", "", "", "", ""));
        products.add(new Product("19", "567", "baz", "", "8", "", "boo", "", "", "", "", "", "", ""));
        products.add(new Product("20", "678", "abcdef", "", "23", "", "yupi", "", "", "", "", "", "", ""));
        products.add(new Product("21", "678", "abcdef", "", "13", "", "toot", "", "", "", "", "", "", ""));
        products.add(new Product("22", "789", "wwwwe", "", "123", "", "toot", "", "", "", "", "", "", ""));
        products.add(new Product("23", "789", "tttweww", "", "3", "", "toot", "", "", "", "", "", "", ""));
        products.add(new Product("24", "789", "omglmao", "", "13", "", "yupi", "", "", "", "", "", "", ""));
        products.add(new Product("25", "789", "omgnfao", "", "31", "", "toot", "", "", "", "", "", "", ""));
        products.add(new Product("26", "789", "barbarbaz", "", "13", "", "toot", "", "", "", "", "", "", ""));
        products.add(new Product("27", "789", "foo", "", "6", "", "boo", "", "", "", "", "", "", ""));
        products.add(new Product("28", "789", "bar", "", "7", "", "boo", "", "", "", "", "", "", ""));
        products.add(new Product("29", "789", "baz", "", "8", "", "boo", "", "", "", "", "", "", ""));
        products.add(new Product("30", "789", "doo", "", "11", "", "boom", "", "", "", "", "", "", ""));
        products.add(new Product("31", "789", "abcdef", "", "13", "", "toot", "", "", "", "", "", "", ""));
        products.add(new Product("32", "789", "wwwwe", "", "123", "", "toot", "", "", "", "", "", "", ""));
        products.add(new Product("33", "789", "tttweww", "", "3", "", "toot", "", "", "", "", "", "", ""));
        products.add(new Product("34", "789", "omglmao", "", "13", "", "yupi", "", "", "", "", "", "", ""));
        products.add(new Product("35", "789", "omgnfao", "", "31", "", "toot", "", "", "", "", "", "", ""));
        products.add(new Product("36", "789", "barbarbaz", "", "13", "", "toot", "", "", "", "", "", "", ""));
        products.add(new Product("37", "789", "foo", "", "6", "", "boo", "", "", "", "", "", "", ""));
        products.add(new Product("38", "789", "bar", "", "7", "", "boo", "", "", "", "", "", "", ""));
        products.add(new Product("39", "789", "baz", "", "8", "", "boo", "", "", "", "", "", "", ""));
        products.add(new Product("40", "789", "doo", "", "11", "", "boom", "", "", "", "", "", "", ""));
    }

}