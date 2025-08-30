package project.IntegratedSystem.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignupDTO {

    @NotBlank(message = "{login.blank.id}")
    @Size(min = 4, max = 20, message = "{signup.user.id.error.length}")
    @Pattern(regexp = "^[a-z0-9]+$", message = "{signup.user.id.error.format}")
    private String userid;

    @NotBlank(message = "{login.blank.password}")
    @Size(min = 4, max = 16, message = "{signup.user.password.error.length}")
    // @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{4,16}$", message = "{signup.user.password.error.combination}")
    private String password;

    @NotBlank(message = "{login.blank.name}")
    private String name;

    @NotBlank(message = "{login.blank.phone}")
    private String phone;
}
