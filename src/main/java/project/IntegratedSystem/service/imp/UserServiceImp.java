package project.IntegratedSystem.service.imp;

import lombok.AllArgsConstructor;
import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.springframework.stereotype.Service;
import project.IntegratedSystem.dto.user.UserDTO;
import project.IntegratedSystem.entity.UserEntity;
import project.IntegratedSystem.mapper.UserMapper;
import project.IntegratedSystem.repository.UserRepository;
import project.IntegratedSystem.service.UserService;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<UserDTO> getList() {
        List<UserEntity> entities = userRepository.findAll();
        return entities.stream()
                .map(UserMapper::toDTO)
                .toList();
    }

    @Override
    public void getProfile() {

    }

    @Override
    public void save(List<UserDTO> user) {
        List<UserEntity> entities = user.stream()
                .map(UserMapper::toEntity)
                .toList();

        userRepository.saveAll(entities);
    }

    @Override
    public UserDTO detail(Integer id) {
        UserEntity entity = userRepository.findById(id).orElseThrow(
                () -> new IllegalIdentifierException("해당 직원이 없습니다."));
        return UserMapper.toDTO(entity);
    }

    @Override
    public void updateUser(Integer id, UserDTO userDTO) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new IllegalIdentifierException("해당 사용자를 찾을 수 없습니다. id: " + id));

        userEntity.setRole(userDTO.getRole());

        userRepository.save(userEntity);
    }

    @Override
    public void delete(Integer id) {
        userRepository.deleteById(id);
    }
}
