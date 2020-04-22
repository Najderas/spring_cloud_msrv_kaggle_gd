package com.github.najderas.catalogservice.service;


public class Product implements java.io.Serializable {

    public String uniq_id;
    public String sku;
    public String name_title;
    public String description;
    public String list_price;
    public String sale_price;
    public String category;
    public String category_tree;
    public String average_product_rating;
    public String product_url;
    public String product_image_urls;
    public String brand;
    public String total_number_reviews;
    public String reviews;

    public Product(String uniq_id) {
        this(uniq_id, "", "", "", "", "", "", "", "", "", "", "", "", "");
    }

    public Product(String uniq_id, String sku, String name_title, String description, String list_price, String sale_price,
                String category, String category_tree, String average_product_rating, String product_url, String product_image_urls,
                String brand, String total_number_reviews, String reviews) {

        this.uniq_id = uniq_id;
        this.sku = sku;
        this.name_title = name_title;
        this.description = description;
        this.list_price = list_price;
        this.sale_price = sale_price;
        this.category = category;
        this.category_tree = category_tree;
        this.average_product_rating = average_product_rating;
        this.product_url = product_url;
        this.product_image_urls = product_image_urls;
        this.brand = brand;
        this.total_number_reviews = total_number_reviews;
        this.reviews = reviews;
                }

}