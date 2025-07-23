package project.IntegratedSystem.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class AuthController {

    @GetMapping("/login")
    public String loginPage() {
        return "login/loginPage";
    }

}
