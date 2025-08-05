package project.IntegratedSystem.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import project.IntegratedSystem.dto.LoginDTO;
import project.IntegratedSystem.service.AuthService;
import project.IntegratedSystem.service.UserService;

import java.util.List;

@Controller
@AllArgsConstructor
public class LoginController {

     UserService userService;
     AuthService authService;

    @GetMapping("/login")
    public String login() {
        return "login/loginPage";
    }

    @GetMapping("/signup")
    public String signup() {
        return "login/signupPage";
    }

    @GetMapping("/userList")
    public String userList(Model model) {
        List<LoginDTO> user = userService.getList();
        model.addAttribute("userList", user);
        return "login/userList";
    }
}
