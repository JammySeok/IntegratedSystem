package project.IntegratedSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.IntegratedSystem.entity.LoginEntity;

public interface LoginRepository extends JpaRepository <LoginEntity, Integer> {
}
