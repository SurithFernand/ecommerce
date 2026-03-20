package com.quickcart.ecommerce.service;

import com.quickcart.ecommerce.model.Product;
import com.quickcart.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    @Override
    public void saveProduct(Product product){
        this.productRepository.save(product);
    }
}
