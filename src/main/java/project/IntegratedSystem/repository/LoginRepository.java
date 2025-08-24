package project.IntegratedSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.IntegratedSystem.entity.EmployeeEntity;
import project.IntegratedSystem.entity.LoginEntity;

import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository <LoginEntity, Integer> {

    Optional<LoginEntity> findByUserid(String userid);
    boolean existsByEmployee(EmployeeEntity employee);
}
