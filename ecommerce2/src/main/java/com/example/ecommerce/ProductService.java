package com.example.ecommerce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    // Constructor Injection (BEST)
    // No @Autowired needed for Constructor Injection
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Get all
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    // Create
    public Product addProduct(Product product){
        return productRepository.save(product);
    }

    // Get by ID (with proper error)
    public Product getProductById(Long id){
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }

    // Update
    public Product updateProduct(Long id, Product updatedProduct){
        return productRepository.findById(id)
                .map(product -> {
                    product.setName(updatedProduct.getName());
                    product.setPrice(updatedProduct.getPrice());
                    product.setDescription(updatedProduct.getDescription());
                    return productRepository.save(product);
                })
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }

    // Delete
    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }
}

//@Service
//public class ProductService {
//
//    private final ProductRepository productRepository;
//
//    @Autowired
//    public ProductService(ProductRepository productRepository) {
//        this.productRepository = productRepository;
//    }
//
//    public Product getProduct(Long id){
//        return productRepository.getById(id);
//    }
//
//    public List<Product> getAllProducts() {
//        return productRepository.findAll();
//    }
//
//    public Product saveProduct(Product product) {
//        return productRepository.save(product);
//    }
//
//    public void deleteProduct(Long id) {
//        productRepository.deleteById(id);
//    }
//}


//@Service
//public class ProductService {
//    @Autowired
//    private ProductRepository productRepository;
//
//    public List<Product> getAllProducts(){
//        return productRepository.findAll();
//    }
//
//    public Product addProduct(Product product){
//        return productRepository.save(product);
//    }
//
//    public Product getProductById(Long id){
//        Optional<Product> optional = productRepository.findById(id);
//        Product product = null;
//        if (optional.isPresent()){
//            product = optional.get();
//        }else {
//            throw new RuntimeException("Product Not Found for the id: " + id);
//        }
//        return product;
//    }
//
//    public Product updateProduct(Long id, Product updatedProduct){
//        return productRepository.findById(id)
//                .map(product -> {
//                    product.setName(updatedProduct.getName());
//                    product.setPrice(updatedProduct.getPrice());
//                    product.setDescription(updatedProduct.getDescription());
//                    return productRepository.save(product);
//                }).orElse(null);
//    }
//
//    public void deleteProduct(Long id){
//        productRepository.deleteById(id);
//    }
//
//}
