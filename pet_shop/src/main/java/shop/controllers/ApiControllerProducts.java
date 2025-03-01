package shop.controllers;

import java.security.Principal;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import shop.model.Product;
import shop.model.Supplier;
import shop.model.User;
import shop.repository.ProductRepository;
import shop.repository.SupplierRepository;
import shop.repository.UserRepository;

@RestController
public class ApiControllerProducts {
    
    private ProductRepository productRepo;
    private SupplierRepository supplierRepo;
    private UserRepository userRepo;

    public ApiControllerProducts(ProductRepository productRepo, SupplierRepository supplierRepo,UserRepository userRepo){
        this.productRepo = productRepo;
        this.supplierRepo = supplierRepo;
        this.userRepo = userRepo;
    }
    
    @GetMapping("/api/v1/products")
    public Iterable<Product> products(){
        return productRepo.findAll();
    }
    
    @GetMapping("/api/v1/products/{id}")
    public Optional<Product> product(@PathVariable Integer id){
        return productRepo.findById(id);
    }

    @GetMapping("/api/v1/suppliers")
    public Iterable<Supplier> suppliers(){
        return supplierRepo.findAll();
    }

    @GetMapping("api/v1/current-user")
    public User user(Principal principal){
        User user = userRepo.findByUsername(principal.getName());

        return user; 
    }
}
