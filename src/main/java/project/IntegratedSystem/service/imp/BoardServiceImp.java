package project.IntegratedSystem.service.imp;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import project.IntegratedSystem.dto.BoardDTO;
import project.IntegratedSystem.entity.BoardEntity;
import project.IntegratedSystem.entity.EmployeeEntity;
import project.IntegratedSystem.mapper.BoardMapper;
import project.IntegratedSystem.mapper.EmployeeMapper;
import project.IntegratedSystem.repository.BoardRepository;
import project.IntegratedSystem.service.BoardService;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BoardServiceImp implements BoardService {

    private final BoardRepository boardRepository;

    public List<BoardDTO> getList() {
        List<BoardEntity> entities = boardRepository.findAllWithAuthor();
        return entities.stream()
                .map(BoardMapper::toDTO)
                .toList();
    }

    public Optional<BoardDTO> findById(Integer id) {
        Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(id);
        return optionalBoardEntity.map(BoardMapper::toDTO);
    }

    public void save(BoardDTO boardDTO) {
        BoardEntity boardEntity = BoardMapper.toEntity(boardDTO);
        boardRepository.save(boardEntity);
    }

    public void addBoard(List<BoardDTO> board) {
        List<BoardEntity> entities = board.stream()
                .map(BoardMapper::toEntity)
                .toList();

        boardRepository.saveAll(entities);
    }

    public void update(BoardDTO boardDTO) {
        BoardEntity boardEntity = BoardMapper.toEntity(boardDTO);

        boardRepository.save(boardEntity);
    }
}
