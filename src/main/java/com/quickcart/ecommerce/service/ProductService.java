package com.quickcart.ecommerce.service;

import com.quickcart.ecommerce.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    void saveProduct(Product product);
}
