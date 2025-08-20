package project.IntegratedSystem.service.imp;

import lombok.AllArgsConstructor;
import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.springframework.stereotype.Service;
import project.IntegratedSystem.dto.EmployeeDTO;
import project.IntegratedSystem.entity.EmployeeEntity;
import project.IntegratedSystem.mapper.EmployeeMapper;
import project.IntegratedSystem.repository.EmployeeRepository;
import project.IntegratedSystem.service.EmployeeService;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeServiceImp implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public List<EmployeeDTO> getList() {
        List<EmployeeEntity> entities = employeeRepository.findAll();
        return entities.stream()
                .map(EmployeeMapper::toDTO)
                .toList();
    }

    @Override
    public void save(List<EmployeeDTO> employee) {
        List<EmployeeEntity> entities = employee.stream()
                .map(EmployeeMapper::toEntity)
                .toList();

        employeeRepository.saveAll(entities);
    }

    @Override
    public EmployeeDTO detail(Integer id) {
        EmployeeEntity entity = employeeRepository.findById(id).orElseThrow(
                () -> new IllegalIdentifierException("해당 직원이 없습니다."));
        return EmployeeMapper.toDTO(entity);
    }

    @Override
    public void delete(Integer id) {
        employeeRepository.deleteById(id);
    }

}
