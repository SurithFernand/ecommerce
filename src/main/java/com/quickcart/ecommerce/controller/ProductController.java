package com.quickcart.ecommerce.controller;

import com.quickcart.ecommerce.model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/products")    // This means all URLs start with http://localhost:8080/products/
public class ProductController {

    private List<Product> productsList = new ArrayList<>(List.of(
            new Product(1, "Espresso", 2.50),
            new Product(2, "Latte", 3.50),
            new Product(3, "Croissant", 2.00),
            new Product(4, "Chocolate Muffin", 2.25),
            new Product(5, "Americano", 2.75)
    ));

    @RequestMapping("/")     // This means all URLs start with http://localhost:8080/products/
    @ResponseBody
    public String home(){
        return "Welcome to the Coffee Shop!";
    }

//    @RequestMapping("/list")     // This maps to the URL http://localhost:8080/products/list
//    @ResponseBody
//    public String listProducts(){
//        String productDisplay = "<strong>Product List:</strong> <hr>";
//        for (Product product : productList){
//            productDisplay += "Product: " + product.getId() + " - " + product.getName() + " - $" + product.getPrice() + "<br>";
//        }
//        return productDisplay;
//    }

    @RequestMapping("/list") // This maps to the URL http://localhost:8080/products/list
    public String listProducts(Model productListModel) { // Model argument is used to pass data to the view
        productListModel.addAttribute("products", productsList); // Add the productsList to the model
        return "menu";  // This returns the view name, that is, the HTML file name
    }

    @RequestMapping("/details/{id}")    // This maps to the URL http://localhost:8080/products/details/{id}
    @ResponseBody
    public String getProductDetailsByID(@PathVariable int id){
        for (Product product : productsList){
            if (product.getId() == id) {
                return "<strong>Requested Product Details: </strong> <hr> Product ID: " + product.getId() + "<br> Name: " + product.getName() + "<br> Price: $" + product.getPrice();
            }
        }
        return "Product not found!";
    }

    @RequestMapping("/add")    // Maps to the URL http://localhost:8080/add
    public String showProductForm(Model productAddFormModel){
        productAddFormModel.addAttribute("product", new Product());     //Should make a constructor without parameters at the model
        return "add-new-product";
    }

    @PostMapping("/addNewProduct")     // Handles the form submission
    public String addProduct(Product product){
        productsList.add(product);
        System.out.println(productsList);   // Logs the updated product list
        return "redirect:/products/list";   // Redirects back to the main product list view
    }
}

