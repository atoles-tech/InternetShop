package shop.controllers;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import shop.model.Product;
import shop.model.User;
import shop.repository.ProductRepository;
import shop.repository.UserRepository;

@Controller
public class ProductsController {

    private UserRepository userRepo;
    private ProductRepository productRepo;

    public ProductsController(UserRepository userRepo, ProductRepository productRepo) {
        this.userRepo = userRepo;
        this.productRepo = productRepo;
    }

    @GetMapping("/products")
    public String products(Model model,Principal principal) {
        if(principal != null){
            model.addAttribute("username", principal.getName());
        }
        return "products";
    }

    @GetMapping("/products/{id}")
    public String viewProduct(@PathVariable Long id, Model model,Principal principal) {
        if(principal != null){
            model.addAttribute("username", principal.getName());
        }
        Product product = productRepo.findById(id.intValue()).orElse(null);
        if (product == null) {
            return "redirect:/";
        }
        model.addAttribute("product", product);
        return "product";
    }

    @PostMapping("/products/{id}")
    public String addProducts(@PathVariable Long id, Principal principal, Model model) {
        User user = userRepo.findByUsername(principal.getName());
        Product product = productRepo.findById(id.intValue()).orElse(null);

        if (product != null && user != null) {
            user.addProduct(product);
            userRepo.save(user);
        }

        return "redirect:/products";
    }

    @PostMapping("/products/del/{id}")
    public String delProduct(@PathVariable Long id, Principal principal){
        if(principal == null){
            return "redirect:/login";
        }
        userRepo.save(userRepo.findByUsername(principal.getName()).delProduct(id));
        return "redirect:/cart";
    }

    @PostMapping("/products/submit-order")
    public String submitOrder(){
        

        return "redirect:/";
    }

}
