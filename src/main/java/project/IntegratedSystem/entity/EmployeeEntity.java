package project.IntegratedSystem.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import project.IntegratedSystem.enums.EmployeePosition;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "Employee")
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column
    private EmployeePosition position;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name="department_id")
//    private DepartmentEntity department;

    @Column
    private String email;

    @Column(nullable = false)
    private String phone;

    @Column(name = "create_at", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createAt;
}