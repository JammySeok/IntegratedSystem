package project.IntegratedSystem.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.IntegratedSystem.entity.BoardEntity;

import java.util.Optional;

public interface BoardRepository extends JpaRepository <BoardEntity, Integer> {

    /**
     * findById 재정의 (N+1 문제 해결)
     * 게시글 하나를 조회할 때도 연관된 login, employee 정보를 함께 가져옴
     * WHERE 절을 추가하여 특정 id의 게시글만 조회
     */
    @Query("SELECT b FROM BoardEntity b JOIN FETCH b.login l JOIN FETCH l.employee WHERE b.id = :id")
    Optional<BoardEntity> findById(@Param("id") Integer id);

    /**
     * findAll 재정의 (페이징 + N+1 문제 해결)
     * JpaRepository의 기본 findAll(Pageable)을 오버라이드하여 fetch join을 적용
     * Page 객체는 전체 데이터 개수(total elements)가 필요한데, join fetch 시 count 쿼리가 복잡해지는 문제를 해결하기 위해 countQuery를 별도로 작성해주는 것이 가장 안정적임
     */
    @Query(value = "SELECT b FROM BoardEntity b JOIN FETCH b.login l JOIN FETCH l.employee",
            countQuery = "SELECT count(b) FROM BoardEntity b")
    Page<BoardEntity> findAll(Pageable pageable);

    /**
     * 제목/내용/작성자 검색 (페이징 + N+1 문제 해결)
     * 검색하는 기능에도 fetch join과 countQuery를 적용하여 성능을 최적화
     */
    @Query(value = "SELECT b FROM BoardEntity b JOIN FETCH b.login l JOIN FETCH l.employee WHERE b.title LIKE %:keyword%",
            countQuery = "SELECT count(b) FROM BoardEntity b WHERE b.title LIKE %:keyword%")
    Page<BoardEntity> findByTitleContaining(@Param("keyword") String keyword, Pageable pageable);

    @Query(value = "SELECT b FROM BoardEntity b JOIN FETCH b.login l JOIN FETCH l.employee WHERE b.content LIKE %:keyword%",
            countQuery = "SELECT count(b) FROM BoardEntity b WHERE b.content LIKE %:keyword%")
    Page<BoardEntity> findByContentContaining(@Param("keyword") String keyword, Pageable pageable);

    @Query(value = "SELECT b FROM BoardEntity b JOIN FETCH b.login l JOIN FETCH l.employee e WHERE e.name LIKE %:keyword%",
            countQuery = "SELECT count(b) FROM BoardEntity b JOIN b.login l JOIN l.employee e WHERE e.name LIKE %:keyword%")
    Page<BoardEntity> findByWriterNameContaining(@Param("keyword") String keyword, Pageable pageable);
}