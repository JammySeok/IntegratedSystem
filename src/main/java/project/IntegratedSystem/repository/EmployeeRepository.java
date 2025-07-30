package project.IntegratedSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.IntegratedSystem.entity.EmployeeEntity;

@Repository
public interface EmployeeRepository extends JpaRepository <EmployeeEntity, Integer> {
}
