package shop.controllers;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import shop.model.RegistrationForm;
import shop.repository.UserRepository;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    private UserRepository userRepo;
    private PasswordEncoder passwordEncoder;

    public RegistrationController(PasswordEncoder passwordEncoder, UserRepository userRepo) {
        this.passwordEncoder = passwordEncoder;
        this.userRepo = userRepo;
    }

    @GetMapping
    public String registerForm(Model model) {
        model.addAttribute("registrationForm",new RegistrationForm());
        return "registration";
    }

    @PostMapping
    public String processRegistration(@ModelAttribute("registrationForm") RegistrationForm form, BindingResult result) {
        
        if(form.getUsername().isEmpty()){
            result.rejectValue("username","error.username","Имя пользователя не должно быть пустым");
        }
        else if(userRepo.findByUsername(form.getUsername())!=null){
            result.rejectValue("username","error.username","Пользователь с таким именем зарегистрирован");
        }
        
        if(form.getPassword().isEmpty()){
            result.rejectValue("password","error.password","Пароль не может быть пустым");
        }
        
        if(!form.getConfirmPassword().equals(form.getPassword())){
            result.rejectValue("confirmPassword","error.confirmPassword","Пароли не совпадают");
        }
        
        if(result.hasErrors()){
            return "registration";
        }

        userRepo.save(form.toUser(passwordEncoder));
        
        return "redirect:/login";
    }
}
