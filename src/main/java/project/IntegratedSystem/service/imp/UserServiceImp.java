package project.IntegratedSystem.service.imp;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import project.IntegratedSystem.dto.UserDTO;
import project.IntegratedSystem.entity.LoginEntity;
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
    public void update() {

    }

    @Override
    public void delete(Integer id) {
        loginRepository.deleteById(id);
    }
}
