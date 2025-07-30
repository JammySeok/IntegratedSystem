package project.IntegratedSystem.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EmployeeDTO {

    private Integer id;
    private String name;
    private Integer position;
    private String email;
    private String phone;
    private LocalDateTime createAt;
}
