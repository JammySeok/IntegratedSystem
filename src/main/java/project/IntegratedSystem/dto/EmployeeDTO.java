package project.IntegratedSystem.dto;

import lombok.Data;
import project.IntegratedSystem.enums.EmployeePosition;

import java.time.LocalDateTime;

@Data
public class EmployeeDTO {

    private Integer id;
    private String name;
    private EmployeePosition position;
    private String email;
    private String phone;
    private LocalDateTime createAt;
}
