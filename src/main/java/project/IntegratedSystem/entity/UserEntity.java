package project.IntegratedSystem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    private String userid;

    @Column
    private String password;

    @Column
    private String name;

    @Column
    private Integer position;

    @Column
    private String role;
}
