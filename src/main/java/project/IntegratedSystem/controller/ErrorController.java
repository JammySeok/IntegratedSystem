package project.IntegratedSystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {

    @GetMapping("/error/denied")
    public String accessDeniedPage() {
        return "error/denied";
    }
}