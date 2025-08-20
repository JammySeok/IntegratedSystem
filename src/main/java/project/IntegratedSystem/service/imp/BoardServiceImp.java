package project.IntegratedSystem.service.imp;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import project.IntegratedSystem.dto.BoardDTO;
import project.IntegratedSystem.entity.BoardEntity;
import project.IntegratedSystem.mapper.BoardMapper;
import project.IntegratedSystem.repository.BoardRepository;
import project.IntegratedSystem.service.BoardService;

import java.util.List;

@Controller
@AllArgsConstructor
public class BoardServiceImp implements BoardService {

    BoardRepository boardRepository;

    public List<BoardDTO> getList() {

        List<BoardEntity> entities = boardRepository.findAllWithAuthor();

        return entities.stream()
                .map(BoardMapper::toDTO)
                .toList();
    }
}
