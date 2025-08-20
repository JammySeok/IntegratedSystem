package project.IntegratedSystem.service;

import org.springframework.stereotype.Service;
import project.IntegratedSystem.dto.EmployeeDTO;
import project.IntegratedSystem.entity.EmployeeEntity;

import java.util.List;

public interface EmployeeService {

    List<EmployeeDTO> getList();
    void save(List<EmployeeDTO> employees);
    EmployeeDTO detail(Integer id);
    void delete(Integer id);
}
