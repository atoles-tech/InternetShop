package shop.controllers;

import java.security.Principal;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import shop.model.Order;
import shop.model.Product;
import shop.model.User;
import shop.repository.OrderRepository;
import shop.repository.UserRepository;

@Controller()
@RequestMapping("/order")
public class OrderController {

    private OrderRepository orderRepo;
    private UserRepository userRepo;

    public OrderController(OrderRepository orderRepo, UserRepository userRepo) {
        this.orderRepo = orderRepo;
        this.userRepo = userRepo;
    }

    @GetMapping()
    public String order(Order order, Model model, Principal principal){
       
        if(principal == null || userRepo.findByUsername(principal.getName()).getProducts().size()==0){
            return "redirect:/";
        }

        model.addAttribute("order",new Order());
      
        return "cart_submit";
    }

    @PostMapping("/submit")
    public String submitOrder(@ModelAttribute("order") Order order, Model model, Principal principal){
        
        if(principal == null){
            return "redirect:/";
        }

        if(order.getAddress().isEmpty()){
            return "redirect:/order";
        }

        User user = userRepo.findByUsername(principal.getName());

        if(user.getProducts().size()==0){
            return "redirect:/";
        }

        for(Product p: user.getProducts()){
            order.addProduct(p);
        }

        user.setProducts(new ArrayList<Product>());
        user.addOrder(order);

        orderRepo.save(order);
        userRepo.save(user);

        return "redirect:/order/success";
    }

    @GetMapping("/success") 
    public String succesOrder(Model model, Principal principal){
        return "order_success";
    }
}
