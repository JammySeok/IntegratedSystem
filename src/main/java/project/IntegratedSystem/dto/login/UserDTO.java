package project.IntegratedSystem.dto.login;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDTO {

    private Integer id;
    private String userid;
    private String password;
    private Integer role;
    private LocalDateTime createAt;
}
