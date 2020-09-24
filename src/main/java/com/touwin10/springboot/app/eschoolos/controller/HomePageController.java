package com.touwin10.springboot.app.eschoolos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePageController {

    @GetMapping(value = {"/", "/index", "/home", "/eschoolos/home"})
    public String showHomepage() {
        return "public/home/index";
    }
}
