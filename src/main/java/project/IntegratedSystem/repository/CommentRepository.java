package project.IntegratedSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.IntegratedSystem.entity.CommentEntity;

public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {
}
