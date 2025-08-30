package project.IntegratedSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.IntegratedSystem.entity.EmployeeEntity;
import project.IntegratedSystem.entity.UserEntity;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository <UserEntity, Integer> {

    Optional<UserEntity> findByUserid(String userid);
    boolean existsByEmployee(EmployeeEntity employee);
}
