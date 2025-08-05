package project.IntegratedSystem.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LoginDTO {

    private Integer id;
    private String username;
    private String password;
    private Integer role;
    private LocalDateTime createAt;
}
