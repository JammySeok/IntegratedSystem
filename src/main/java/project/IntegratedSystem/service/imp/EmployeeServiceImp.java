package project.IntegratedSystem.service.imp;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import project.IntegratedSystem.entity.EmployeeEntity;
import project.IntegratedSystem.repository.EmployeeRepository;
import project.IntegratedSystem.service.EmployeeService;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeServiceImp implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public List<EmployeeEntity> getList() {
        return employeeRepository.findAll();
    }
}
