package se.jensen.linus.springboot1.Controller;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hello")

public class HelloController {
    @GetMapping
    public String hello() {
        return "Hello Spring Boot!";


    }

    @PostMapping
    public String post(@RequestBody String message) {
        return message + " recevied";


    }
}
