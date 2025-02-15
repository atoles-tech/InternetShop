package shop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ProductsController {
    

    @GetMapping("/products")
    public String products(){
        return "products";
    }

    @GetMapping("/products/{id}")
    public String viewProduct(@PathVariable Long id){
        return "product";
    }
}
