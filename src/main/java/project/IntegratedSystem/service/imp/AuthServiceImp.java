package project.IntegratedSystem.service.imp;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import project.IntegratedSystem.dto.user.SignupDTO;
import project.IntegratedSystem.entity.EmployeeEntity;
import project.IntegratedSystem.entity.UserEntity;
import project.IntegratedSystem.enums.UserRole;
import project.IntegratedSystem.repository.EmployeeRepository;
import project.IntegratedSystem.repository.UserRepository;
import project.IntegratedSystem.service.AuthService;


@Service
@AllArgsConstructor
public class AuthServiceImp implements AuthService {

    private final EmployeeRepository employeeRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder; // 비밀번호 암호화를 위해 추가
    // private final MessageSource messageSource; // 로그인 로직이 주석처리되어 현재 미사용

//    @Override
//    public UserDTO login(LoginDTO loginDTO) {
//
//        String errorMessage = messageSource.getMessage("login.fail.credentials", null, LocaleContextHolder.getLocale());
//
//        LoginEntity loginEntity = loginRepository.findByUserid(loginDTO.getUserid())
//                .orElseThrow(() -> new RuntimeException(errorMessage));
//
//        if (!loginEntity.getPassword().equals(loginDTO.getPassword())) {
//            throw new RuntimeException(errorMessage);
//        }
//
//        return UserMapper.toDTO(loginEntity);
//    }

    @Override
    @Transactional // 여러 DB 작업을 하나의 단위로 묶기 위해 추가
    public void signup(SignupDTO signupDTO) {
        // 1. 아이디 중복 확인
        if (userRepository.findByUserid(signupDTO.getUserid()).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 아이디입니다.");
        }

        // 2. 이름과 연락처로 직원 정보 조회
        EmployeeEntity employeeEntity = employeeRepository.findByNameAndPhone(signupDTO.getName(), signupDTO.getPhone())
                .orElseThrow(() -> new IllegalArgumentException("일치하는 직원 정보가 없습니다. 관리자에게 문의하세요."));

        // 3. 해당 직원 정보로 이미 가입된 계정이 있는지 확인
        if (userRepository.existsByEmployee(employeeEntity)) {
            throw new IllegalArgumentException("이미 계정이 등록된 직원입니다.");
        }

        // 4. 모든 검증 통과 시, LoginEntity 생성 및 저장
        UserEntity userEntity = new UserEntity();
        userEntity.setUserid(signupDTO.getUserid());
        userEntity.setPassword(passwordEncoder.encode(signupDTO.getPassword()));
        userEntity.setRole(UserRole.USER);
        userEntity.setEmployee(employeeEntity);

        userRepository.save(userEntity);
    }
}