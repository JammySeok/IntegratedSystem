package project.IntegratedSystem.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import project.IntegratedSystem.entity.EmployeeEntity;
import project.IntegratedSystem.service.EmployeeService;
import project.IntegratedSystem.service.imp.EmployeeServiceImp;

import java.util.List;

@Controller
@AllArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;
    private final EmployeeServiceImp employeeServiceImp;  // 임시 (DIP 위배)

    @GetMapping("/employeeList")
    public String getEmployeeList(Model model) {
        List<EmployeeEntity> employeeList = employeeServiceImp.getList();
        model.addAttribute("employeeList", employeeList);

        return "employeeListPage";
    }

    @GetMapping("/employeeAdd")
    public String addEmployee() {
        return "employeeAddPage";
    }

}
