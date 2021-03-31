package ru.library.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {

    @RequestMapping(path = {"/", "/index"})
    public String index(){
        return "index";
    }

}
