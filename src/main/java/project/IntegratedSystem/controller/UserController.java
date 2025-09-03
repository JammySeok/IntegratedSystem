package project.IntegratedSystem.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import project.IntegratedSystem.dto.EmployeeDTO;
import project.IntegratedSystem.dto.user.UserDTO;
import project.IntegratedSystem.security.CustomUserDetails;
import project.IntegratedSystem.service.EmployeeService;
import project.IntegratedSystem.service.UserService;

import java.util.List;

@Controller
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final EmployeeService employeeService;

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

    @GetMapping("/userList/update/{id}")
    public String updateForm(@PathVariable("id") Integer id, Model model) {
        UserDTO user = userService.detail(id);
        model.addAttribute("user", user);
        return "login/userUpdate";
    }


    @PostMapping("/userList/update/{id}")
    public String update(@PathVariable("id") Integer id, UserDTO user) {
        userService.updateUser(id, user);

        return "redirect:/userList";
    }

    @GetMapping("/myProfile")
    public String myProfile(@AuthenticationPrincipal CustomUserDetails userDetails, Model model) {
        Integer currentUserId = userDetails.getId();

        UserDTO user = userService.detail(currentUserId);
        Integer employeeId = user.getEmployeeId();

        EmployeeDTO employee = null;
        if (employeeId != null) {
            employee = employeeService.detail(employeeId);
        }

        model.addAttribute("user", user);
        model.addAttribute("employee", employee);

        return "login/myProfile";
    }
}