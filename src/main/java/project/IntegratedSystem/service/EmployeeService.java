package project.IntegratedSystem.service;

import org.springframework.stereotype.Service;
import project.IntegratedSystem.dto.EmployeeDTO;
import project.IntegratedSystem.entity.EmployeeEntity;

import java.util.List;

public interface EmployeeService {

    List<EmployeeEntity> getList();
}
