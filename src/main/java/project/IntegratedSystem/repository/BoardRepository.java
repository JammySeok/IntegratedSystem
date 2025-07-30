package project.IntegratedSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.IntegratedSystem.entity.BoardEntity;

public interface BoardRepository extends JpaRepository <BoardEntity, Integer> {

}
