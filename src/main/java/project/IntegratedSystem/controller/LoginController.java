package project.IntegratedSystem.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.IntegratedSystem.service.UserService;

@Controller
@AllArgsConstructor
public class LoginController {

    public final UserService userService;

    @GetMapping("/login")
    public String loginPage() {
        return "login/loginPage";
    }

    @PostMapping("/login")
    public String login(@RequestParam String id, @RequestParam String password) {
        boolean check = userService.checkLogin(id, password);

        if(check) {
            return "redirect:/";
        }
        else {
            return "redirect:/login";
        }
    }

    @GetMapping("/register")
    public String registerPage() {
        return "/login/registerPage";
    }

    @PostMapping("/register")
    public String register(@RequestParam String id, @RequestParam String password, @RequestParam String name) {
        userService.addList(id, password, name);

        return "redirect:/login";
    }
}
