package com.touwin10.springboot.app.eschoolos.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExtraPageController {

    @GetMapping(value = {"/extra/login", "/eschoolos/extra/login"})
    public String signinPage() {
        return "public/pages/signin";
    }

    @GetMapping(value = {"/extra/signup", "/eschoolos/extra/signup"})
    public String signUpPage() {
        return "public/pages/signup";
    }

    @GetMapping(value = {"/extra/page-404", "/eschoolos/extra/page-404"})
    public String page404() {
        return "public/pages/404";
    }

    @GetMapping(value = {"/extra/page-500", "/eschoolos/extra/page-500"})
    public String page500() {
        return "public/pages/500";
    }

    @GetMapping(value = {"/extra/page-blank", "/eschoolos/extra/page-blank"})
    public String pageBlank() {
        return "public/pages/blank";
    }

}
