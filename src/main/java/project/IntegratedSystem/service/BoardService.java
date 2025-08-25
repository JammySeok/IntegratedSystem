package project.IntegratedSystem.service;

import project.IntegratedSystem.dto.BoardDTO;

import java.util.List;
import java.util.Optional;

public interface BoardService {

    List<BoardDTO> getList();
    Optional<BoardDTO> findById(Integer id);
    void create(BoardDTO boardDTO, String currentUserId);
    void update(Integer boardId, BoardDTO boardDTO, String currentUserId);
    void delete(Integer id, String currentUserId);

    void save(BoardDTO boardDTO);
    void addBoard(List<BoardDTO> board);
}
