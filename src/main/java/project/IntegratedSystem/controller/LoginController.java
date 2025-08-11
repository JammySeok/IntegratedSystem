package project.IntegratedSystem.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import project.IntegratedSystem.dto.LoginDTO;
import project.IntegratedSystem.service.AuthService;
import project.IntegratedSystem.service.UserService;

import java.util.List;

@Controller
@AllArgsConstructor
public class LoginController {

    private final AuthService authService;
    private final UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login/loginPage";
    }

    @PostMapping("/login")
    public String loginAuth(@ModelAttribute LoginDTO loginDTO, Model model) {
        try {
            authService.login(loginDTO);
            return "redirect:/";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "login/loginPage";
        }
    }

    @GetMapping("/signup")
    public String signup() {
        return "login/signupPage";
    }

    @PostMapping("/signup")
    public String addUser(@ModelAttribute LoginDTO login) {
        authService.signup(List.of(login));
        return "redirect:/login";
    }

    @GetMapping("/userList")
    public String userList(Model model) {
        List<LoginDTO> user = userService.getList();
        model.addAttribute("userList", user);
        return "login/userList";
    }
}
