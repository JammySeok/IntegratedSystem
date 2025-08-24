package project.IntegratedSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.IntegratedSystem.entity.EmployeeEntity;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository <EmployeeEntity, Integer> {

    Optional<EmployeeEntity> findByNameAndPhone(String name, String phone);
}
