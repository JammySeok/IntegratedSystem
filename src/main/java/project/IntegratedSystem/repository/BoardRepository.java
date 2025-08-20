package project.IntegratedSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import project.IntegratedSystem.entity.BoardEntity;

import java.util.List;

public interface BoardRepository extends JpaRepository <BoardEntity, Integer> {

    // N+1 문제를 해결하기 위한 JOIN FETCH 쿼리 추가
    @Query("SELECT b FROM BoardEntity b JOIN FETCH b.login l JOIN FETCH l.employee")
    List<BoardEntity> findAllWithAuthor();
}
