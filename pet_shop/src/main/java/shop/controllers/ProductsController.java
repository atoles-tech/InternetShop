package shop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import shop.repository.ProductRepository;

@Controller
public class ProductsController {
    
    private ProductRepository productRepo;

    public ProductsController(ProductRepository productRepo){
        this.productRepo = productRepo;
    }

    @GetMapping("/products")
    public String products(){
        return "products";
    }
}
