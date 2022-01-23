package com.miesitu.web_project.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

@SessionAttributes
@Controller
public class AboutController {
    
    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("msg", "Welcome to the Netherlands!");
    }

    @GetMapping("/about")
    public String about(Model model){
        String username = "Not Both";
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
            System.out.print("\n\n\n");
            System.out.print(((UserDetails)principal));
        } else {
            username = principal.toString();
        }

        System.out.print("\n\n\n");

        System.out.print(username);
        return "about";
    }

    
    
}
