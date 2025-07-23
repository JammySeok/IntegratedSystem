package project.IntegratedSystem.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import project.IntegratedSystem.entity.UserEntity;
import project.IntegratedSystem.service.UserService;

import java.util.List;

@AllArgsConstructor
@Controller
public class UserController {

    public final UserService userService;

    @GetMapping("/user")
    public String getUser(Model model) {

        List<UserEntity> userList = userService.getList();
        model.addAttribute("userList", userList);

        return "userList";
    }
}
