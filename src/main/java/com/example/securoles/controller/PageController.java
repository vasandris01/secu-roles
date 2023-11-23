package com.example.securoles.controller;


import com.example.securoles.enums.Role;
import com.example.securoles.model.MyUser;
import com.example.securoles.services.MyUserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@AllArgsConstructor
public class PageController {
    private MyUserDetailsService userServices;
    private PasswordEncoder passwordEncoder;
    @GetMapping("/admin")
    public String getAdminPage(){
        return "admin";
    }

    @GetMapping("/user")
    public String getUserPage(){
        return "user";
    }

    @GetMapping("/reg")
    public String getRegPage(//*Model model*//
                              ){
//        model.addAttribute("role", false);
        return "reg";
    }

    @PostMapping("/reg")
    public String regUser(@RequestParam("name") String name,
                          @RequestParam("password") String password,
                          @RequestParam("role") String role){
        MyUser user = new MyUser();
        user.setName(name);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(role.equalsIgnoreCase("ADMIN") ? Role.ADMIN : Role.USER);
        userServices.saveUser(user);
        return "redirect:/login";
    }
}
