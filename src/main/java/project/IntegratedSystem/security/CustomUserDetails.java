package project.IntegratedSystem.security;

import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import project.IntegratedSystem.entity.UserEntity;

import java.util.Collections;

@Getter
public class CustomUserDetails extends User {

    private final Integer id;

    public CustomUserDetails(UserEntity userEntity) {
        super(
                userEntity.getUserid(),
                userEntity.getPassword(),
                Collections.singleton(new SimpleGrantedAuthority("ROLE_" + userEntity.getRole().name()))
                );

        this.id = userEntity.getId();
    }
}