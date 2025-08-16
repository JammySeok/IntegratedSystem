package project.IntegratedSystem.service.imp;

import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import project.IntegratedSystem.dto.login.LoginDTO;
import project.IntegratedSystem.dto.login.SignupDTO;
import project.IntegratedSystem.dto.login.UserDTO;
import project.IntegratedSystem.entity.LoginEntity;
import project.IntegratedSystem.mapper.UserMapper;
import project.IntegratedSystem.repository.LoginRepository;
import project.IntegratedSystem.service.AuthService;

@Service
@AllArgsConstructor
public class AuthServiceImp implements AuthService {

    private final LoginRepository loginRepository;
    private final MessageSource messageSource;

    @Override
    public UserDTO login(LoginDTO loginDTO) {

        String errorMessage = messageSource.getMessage("login.fail.credentials", null, LocaleContextHolder.getLocale());

        LoginEntity loginEntity = loginRepository.findByUserid(loginDTO.getUserid())
                .orElseThrow(() -> new RuntimeException(errorMessage));

        if (!loginEntity.getPassword().equals(loginDTO.getPassword())) {
            throw new RuntimeException(errorMessage);
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
