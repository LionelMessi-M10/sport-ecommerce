package com.sport.controller.user;

import com.sport.model.dto.UserDTO;
import com.sport.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequiredArgsConstructor
@Controller
@RequestMapping("/shop")
public class UserController {

    private final UserService userService;

    @GetMapping("/login")
    public String login(){
        return "web/login";
    }

    @GetMapping("/register")
    public ModelAndView register(@ModelAttribute("userDTO") UserDTO userDTO){
        return new ModelAndView("web/register");
    }

    @PostMapping("/register")
    public ModelAndView registerAccount(@ModelAttribute("userDTO") UserDTO userDTO){
        ModelAndView modelAndView = new ModelAndView("web/login");
        userService.saveUserDTO(userDTO);
        return modelAndView;
    }

    @GetMapping("/profile")
    public String profile(@ModelAttribute("userDTO") UserDTO userDTO, Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication != null && authentication.isAuthenticated()){
            userDTO = userService.findByUsername(authentication.getName());
            model.addAttribute("userDTO", userDTO);
            return "web/profile";
        }
        return "redirect:/shop/login?not-login";
    }

    @PostMapping("/profile")
    public String saveProfile(@ModelAttribute("userDTO") UserDTO userDTO){
        userService.saveUserDTO(userDTO);
        return "redirect:/shop/home";
    }

    @GetMapping("/password-reset")
    public String resetPassword(){
        return "web/password-reset";
    }

    @GetMapping("/showPage403")
    public String errorPage(){
        return "web/403-Page";
    }
}
