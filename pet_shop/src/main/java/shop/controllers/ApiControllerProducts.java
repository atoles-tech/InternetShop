package shop.controllers;

import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import shop.model.Product;
import shop.repository.ProductRepository;

@RestController
public class ApiControllerProducts {
    
    private ProductRepository productRepo;

    public ApiControllerProducts(ProductRepository productRepo){
        this.productRepo = productRepo;
    }
    
    @GetMapping("/api/v1/products")
    public Iterable<Product> products(){
        return productRepo.findAll();
    }
    
    @GetMapping("api/v1/products/{id}")
    public Optional<Product> product(@PathVariable Integer id){
        return productRepo.findById(id);
    }
}
