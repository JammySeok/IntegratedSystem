package project.IntegratedSystem.service.imp;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import project.IntegratedSystem.dto.LoginDTO;
import project.IntegratedSystem.dto.SignupDTO;
import project.IntegratedSystem.dto.UserDTO;
import project.IntegratedSystem.entity.LoginEntity;
import project.IntegratedSystem.mapper.UserMapper;
import project.IntegratedSystem.repository.LoginRepository;
import project.IntegratedSystem.service.AuthService;

import java.util.List;

@Service
@AllArgsConstructor
public class AuthServiceImp implements AuthService {

    private final LoginRepository loginRepository;

    @Override
    public UserDTO login(LoginDTO loginDTO) {
        LoginEntity loginEntity = loginRepository.findByUserid(loginDTO.getUserid())
                .orElseThrow(() -> new RuntimeException("해당 사용자가 존재하지 않습니다."));

        if (!loginEntity.getPassword().equals(loginDTO.getPassword())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }

        return UserMapper.toDTO(loginEntity);
    }

    @Override
    public void signup(SignupDTO signupDTO) {
        if (loginRepository.findByUserid(signupDTO.getUserid()).isPresent()) {
            throw new RuntimeException("이미 존재하는 아이디입니다.");
        }

        LoginEntity entity = UserMapper.toEntity(signupDTO);

        loginRepository.save(entity);
    }
}
