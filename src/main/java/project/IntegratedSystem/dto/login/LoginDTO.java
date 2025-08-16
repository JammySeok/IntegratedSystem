package project.IntegratedSystem.dto.login;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginDTO {

    @NotBlank(message = "{login.blank.id}")
    private String userid;

    @NotBlank(message = "{login.blank.password}")
    private String password;
}
