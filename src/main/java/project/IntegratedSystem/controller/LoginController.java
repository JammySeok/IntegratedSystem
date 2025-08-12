package project.IntegratedSystem.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import project.IntegratedSystem.dto.LoginDTO;
import project.IntegratedSystem.dto.SignupDTO;
import project.IntegratedSystem.dto.UserDTO;
import project.IntegratedSystem.service.AuthService;
import project.IntegratedSystem.service.UserService;

import java.util.List;

@Controller
@AllArgsConstructor
public class LoginController {

    private final AuthService authService;
    private final UserService userService;

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("loginDTO", new LoginDTO());
        return "login/loginPage";
    }

    @PostMapping("/login")
    public String loginAuth(@ModelAttribute LoginDTO loginDTO, Model model, HttpSession session) {
        try {
            UserDTO loginUser = authService.login(loginDTO);
            session.setAttribute("loginUser", loginUser);
            return "redirect:/";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "login/loginPage";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/signup")
    public String signupForm(Model model) {
        model.addAttribute("signupDTO", new SignupDTO());
        return "login/signupPage";
    }

    @PostMapping("/signup")
    public String addUser(@Valid @ModelAttribute SignupDTO signupDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "login/signupPage";
        }
        try {
            authService.signup(signupDTO);
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "login/signupPage";
        }

        return "redirect:/login";
    }

    @GetMapping("/userList")
    public String userList(Model model) {
        List<UserDTO> user = userService.getList();
        model.addAttribute("userList", user);
        return "login/userList";
    }

    @PostMapping("/userList/{id}")
    public String delete(@PathVariable("id") Integer id) {
        userService.delete(id);
        return "redirect:/userList";
    }
}