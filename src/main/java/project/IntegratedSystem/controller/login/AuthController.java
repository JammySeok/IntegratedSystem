package project.IntegratedSystem.controller.login;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import project.IntegratedSystem.dto.login.LoginDTO;
import project.IntegratedSystem.dto.login.SignupDTO;
import project.IntegratedSystem.dto.login.UserDTO;
import project.IntegratedSystem.service.AuthService;

@Controller
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("loginDTO", new LoginDTO());
        return "login/loginPage";
    }

//    @PostMapping("/login")
//    public String loginAuth(@Valid @ModelAttribute LoginDTO loginDTO, BindingResult bindingResult, Model model, HttpSession session) {
//        if (bindingResult.hasErrors()) {
//            return "login/loginPage";
//        }
//
//        try {
//            UserDTO loginUser = authService.login(loginDTO);
//            session.setAttribute("loginUser", loginUser);
//            return "redirect:/";
//        } catch (RuntimeException e) {
//            model.addAttribute("error", e.getMessage());
//            return "login/loginPage";
//        }
//    }

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
}