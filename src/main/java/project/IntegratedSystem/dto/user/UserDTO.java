package project.IntegratedSystem.dto.user;

import lombok.Data;
import project.IntegratedSystem.enums.UserRole;

import java.time.LocalDateTime;

@Data
public class UserDTO {

    private Integer id;
    private String userid;
    private String password;
    private UserRole role;
    private LocalDateTime createAt;
}
