package project.IntegratedSystem.dto;

import lombok.Data;
import project.IntegratedSystem.entity.LoginEntity;

import java.time.LocalDateTime;

@Data
public class UserDTO {

    private Integer id;
    private String userid;
    private String password;
    private Integer role;
    private LocalDateTime createAt;
}
