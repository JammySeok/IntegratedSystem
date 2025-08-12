package project.IntegratedSystem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SignupDTO {

    private Integer id;

    @NotBlank(message = "{login.blank.id}")
    @Size(min = 4, max = 20, message = "{signup.user.id.error.length}")
    @Pattern(regexp = "^[a-z0-9]+$", message = "{signup.user.id.error.format}")
    private String userid;

    @NotBlank(message = "{login.blank.password}")
    @Size(min = 4, max = 16, message = "{signup.user.password.error.length}")
    // @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{4,16}$", message = "{signup.user.password.error.combination}")
    private String password;

    private Integer role;
    private LocalDateTime createAt;
}
