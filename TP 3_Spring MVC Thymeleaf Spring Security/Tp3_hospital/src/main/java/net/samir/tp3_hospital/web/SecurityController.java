package net.samir.tp3_hospital.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {


    @GetMapping("/notAuthorized")
    public String notAuthorized(){
        return "not authorized";
    }
    @GetMapping("/login")
    public String login(){
        return "login";
    }
}
