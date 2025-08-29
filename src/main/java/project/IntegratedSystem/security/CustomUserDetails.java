package project.IntegratedSystem.security;

import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import project.IntegratedSystem.entity.LoginEntity;

import java.util.Collections;

@Getter
public class CustomUserDetails extends User {

    private final Integer id;

    public CustomUserDetails(LoginEntity loginEntity) {
        super(
                loginEntity.getUserid(),
                loginEntity.getPassword(),
                Collections.singleton(new SimpleGrantedAuthority("ROLE_" + loginEntity.getRole().name()))
                );

        this.id = loginEntity.getId();
    }
}