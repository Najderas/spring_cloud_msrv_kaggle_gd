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
        products = new ArrayList();
        // products.add(new Product("swkokurdealetojestbrzydkiemehnonic"));
        // products.add(new Product("wkrnojaniemogejuzilemozna"));
        // products.add(new Product("prwjkskonczwascwstydioszczedzjnmg"));

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
        products.add(new Product("11", "678", "abcdef", "", "23", "", "yupi", "", "", "", "", "", "", ""));
    }
    

    public List<Product> allProducts() {
        // List<Product> list = new ArrayList();
        // list.add(new Product("swkokurdealetojestbrzydkiemehnonic"));
        // list.add(new Product("wkrnojaniemogejuzilemozna"));
        // list.add(new Product("prwjkskonczwascwstydioszczedzjnmg"));
        // return list;
        return products;
    }

    public Optional<Product> findProductByCode(String code) {
        // return Optional.ofNullable(products.get(0));
        List<Product> matches = products.stream().filter(p -> p.uniq_id.equals(code)).collect(Collectors.toList());
        // if (matches.isEmpty()) {
        //     return Optional.empty();
        // } else {
        //     return Optional.ofNullable(matches.get(0));
        // }

        return Optional.ofNullable(matches.isEmpty() ? null : matches.get(0));
    }

    public List<Product> findProductsBySku(String sku) {
        List<Product> matches = products.stream().filter(p -> p.sku.equals(sku)).collect(Collectors.toList());
        
        return matches;
    }

}