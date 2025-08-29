package project.IntegratedSystem.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import project.IntegratedSystem.entity.LoginEntity;
import project.IntegratedSystem.repository.LoginRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImp  implements UserDetailsService {

    private final LoginRepository loginRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String userid) throws UsernameNotFoundException {
        LoginEntity loginEntity = loginRepository.findByUserid(userid)
                .orElseThrow(() -> new UsernameNotFoundException("해당하는 유저를 찾을 수 없습니다: " + userid));

        return new CustomUserDetails(loginEntity);
    }
}
