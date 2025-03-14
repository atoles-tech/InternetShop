package shop.controllers;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model, Principal principal) {

        if (principal != null) {
            model.addAttribute("username", principal.getName());
        }

        return "home";
    }

    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error, Model model,Principal principal) {

        if (principal != null) {
            return "redirect:/";
        }
       
        if (error != null) {
            model.addAttribute("errorMessage", "Неверный логин или пароль.");
        }

        return "login";
    }

    @GetMapping("/cart")
    public String cart() {
        return "cart";
    }

    @GetMapping("/about-us")
    public String aboutUs(Model model, Principal principal) {

        if(principal != null){
            model.addAttribute("username",principal.getName());
        }

        return "about_us";
    }

    @GetMapping("/way-delivery")
    public String wayDelivery(Model model, Principal principal) {

        if(principal != null){
            model.addAttribute("username",principal.getName());
        }

        return "way_delivery";
    }

    @GetMapping("/orders")
    public String orders(){
        return "orders";
    }
}
