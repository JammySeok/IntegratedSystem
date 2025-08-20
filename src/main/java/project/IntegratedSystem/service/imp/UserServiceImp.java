package project.IntegratedSystem.service.imp;

import lombok.AllArgsConstructor;
import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.springframework.stereotype.Service;
import project.IntegratedSystem.dto.EmployeeDTO;
import project.IntegratedSystem.dto.login.UserDTO;
import project.IntegratedSystem.entity.EmployeeEntity;
import project.IntegratedSystem.entity.LoginEntity;
import project.IntegratedSystem.mapper.EmployeeMapper;
import project.IntegratedSystem.mapper.UserMapper;
import project.IntegratedSystem.repository.LoginRepository;
import project.IntegratedSystem.service.UserService;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImp implements UserService {

    private final LoginRepository loginRepository;

    @Override
    public List<UserDTO> getList() {
        List<LoginEntity> entities = loginRepository.findAll();
        return entities.stream()
                .map(UserMapper::toDTO)
                .toList();
    }

    @Override
    public void getProfile() {

    }

    @Override
    public void save(List<UserDTO> user) {
        List<LoginEntity> entities = user.stream()
                .map(UserMapper::toEntity)
                .toList();

        loginRepository.saveAll(entities);
    }

    @Override
    public UserDTO detail(Integer id) {
        LoginEntity entity = loginRepository.findById(id).orElseThrow(
                () -> new IllegalIdentifierException("해당 직원이 없습니다."));
        return UserMapper.toDTO(entity);
    }

    @Override
    public void updateUser(Integer id, UserDTO userDTO) {
        LoginEntity loginEntity = loginRepository.findById(id)
                .orElseThrow(() -> new IllegalIdentifierException("해당 사용자를 찾을 수 없습니다. id: " + id));

        loginEntity.setRole(userDTO.getRole());

        loginRepository.save(loginEntity);
    }

    @Override
    public void delete(Integer id) {
        loginRepository.deleteById(id);
    }
}
