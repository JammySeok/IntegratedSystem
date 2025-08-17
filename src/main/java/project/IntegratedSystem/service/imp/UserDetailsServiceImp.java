package project.IntegratedSystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.IntegratedSystem.entity.LoginEntity;
import project.IntegratedSystem.repository.LoginRepository;

import java.util.Collections;

@Service
@RequiredArgsConstructor // final 필드에 대한 생성자를 자동으로 생성해줍니다.
public class UserDetailsServiceImp implements UserDetailsService {

    private final LoginRepository loginRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String userid) throws UsernameNotFoundException {
        // LoginRepository를 통해 userid로 LoginEntity를 조회합니다.
        LoginEntity loginEntity = loginRepository.findByUserid(userid)
                .orElseThrow(() -> new UsernameNotFoundException("해당하는 유저를 찾을 수 없습니다: " + userid));

        // UserDetails 객체를 생성하여 반환합니다.
        // 이 객체를 Spring Security가 사용하여 인증 및 권한 부여를 처리합니다.
        return createUser(loginEntity);
    }

    private UserDetails createUser(LoginEntity loginEntity) {
        // 사용자의 Role 정보를 GrantedAuthority 객체로 변환합니다.
        // Spring Security는 역할(Role) 앞에 "ROLE_" 접두사가 붙는 것을 기본으로 합니다.
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_" + loginEntity.getRole().name());

        return new User(
                loginEntity.getUserid(), // UserDetails의 username
                loginEntity.getPassword(), // UserDetails의 password (암호화된 값)
                Collections.singleton(grantedAuthority) // 사용자의 권한 목록
        );
    }
}