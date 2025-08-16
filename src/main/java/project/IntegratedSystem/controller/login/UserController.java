package project.IntegratedSystem.controller.login;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import project.IntegratedSystem.dto.login.UserDTO;
import project.IntegratedSystem.service.UserService;

import java.util.List;

@Controller
@AllArgsConstructor
public class UserController {

    private final UserService userService;

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