package com.sport.controller.web;

import com.sport.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/shop")
public class HomeController {

    @Autowired
    private ProductService productService;

    @GetMapping(value = {"/", "/home"})
    public String home(Model model){
        return "web/home";
    }

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }
}
