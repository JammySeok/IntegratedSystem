package project.IntegratedSystem.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import project.IntegratedSystem.dto.BoardDTO;

import java.util.Optional;

public interface BoardService {

    Page<BoardDTO> getList(String searchType, String keyword, Pageable pageable);
    Optional<BoardDTO> findById(Integer id);
    void create(BoardDTO boardDTO, String currentUserId);
    void update(Integer boardId, BoardDTO boardDTO, String currentUserId);
    void delete(Integer id, String currentUserId);
}
