package com.youcode.pigeonskyracev2;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class UserController {

    @RequestMapping("/hi")
    public String hi() {
        return "hi";
    }
}
