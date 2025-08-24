package project.IntegratedSystem.service;

import project.IntegratedSystem.dto.BoardDTO;

import java.util.List;
import java.util.Optional;

public interface BoardService {
    List<BoardDTO> getList();
    Optional<BoardDTO> findById(Integer id);
    void save(BoardDTO boardDTO);
    void addBoard(List<BoardDTO> board);
    void update(BoardDTO boardDTO);

}
