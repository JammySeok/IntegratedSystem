package project.IntegratedSystem.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import project.IntegratedSystem.dto.EmployeeDTO;
import project.IntegratedSystem.service.EmployeeService;

import java.util.List;

@Controller
@AllArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/employeeList")
    public String getEmployeeList(Model model) {
        List<EmployeeDTO> employeeList = employeeService.getList();
        model.addAttribute("employeeList", employeeList);

        return "/employee/employeeListPage";
    }

    @GetMapping("/employeeAdd")
    public String addEmployee() {
        return "employee/employeeAddPage";
    }

    @PostMapping("/employeeAdd")
    public String save(@ModelAttribute EmployeeDTO employee) {
        employeeService.save(List.of(employee));
        return "redirect:/employeeList";
    }

    @PostMapping("/employeeList/{id}")
    public String delete(@PathVariable("id") Integer id) {
        employeeService.delete(id);
        return "redirect:/employeeList";
    }

    @GetMapping("/employeeList/update/{id}")
    public String detail(@PathVariable("id") Integer id, Model model) {
        EmployeeDTO employee = employeeService.detail(id);
        model.addAttribute("employee", employee);
        return "employee/employeeUpdatePage";
    }

    @PostMapping("/employeeList/update/{id}")
    public String update(@PathVariable Integer id, EmployeeDTO employee) {
        employee.setId(id);
        // save 메서드로 update 처리 (id가 존재하면 update)
        employeeService.save(List.of(employee));

        return "redirect:/employeeList";
    }
}
