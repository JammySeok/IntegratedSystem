package project.IntegratedSystem.service.imp;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import project.IntegratedSystem.dto.BoardDTO;
import project.IntegratedSystem.entity.BoardEntity;
import project.IntegratedSystem.entity.LoginEntity;
import project.IntegratedSystem.mapper.BoardMapper;
import project.IntegratedSystem.repository.BoardRepository;
import project.IntegratedSystem.repository.LoginRepository;
import project.IntegratedSystem.service.BoardService;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BoardServiceImp implements BoardService {

    private final LoginRepository loginRepository;
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

    @Transactional
    public void create(BoardDTO boardDTO, String currentUserId) {
        // 1. userid로 LoginEntity 조회
        LoginEntity currentUser = loginRepository.findByUserid(currentUserId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다: " + currentUserId));

        // 2. DTO를 Entity로 변환
        BoardEntity boardEntity = BoardMapper.toEntity(boardDTO);

        // 3. 연관관계 설정 (게시글의 작성자 설정)
        boardEntity.setLogin(currentUser);

        // 4. 저장
        boardRepository.save(boardEntity);
    }

    // [수정] 게시글 수정: 수정할 내용, 게시글 id, 현재 로그인한 유저의 userid를 받음
    @Transactional
    public void update(Integer boardId, BoardDTO boardDTO, String currentUserId) {
        // 1. 수정할 게시글 조회
        BoardEntity boardEntity = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다: " + boardId));

        // 2. 권한 확인
        if (!boardEntity.getLogin().getUserid().equals(currentUserId)) {
            throw new AccessDeniedException("이 게시글은 작성자 본인만 수정할 수 있습니다.");
        }

        // 3. 내용 수정
        boardEntity.setTitle(boardDTO.getTitle());
        boardEntity.setContent(boardDTO.getContent());
    }

    // [추가] 게시글 삭제
    @Transactional
    public void delete(Integer boardId, String currentUserId) {
        // 1. 삭제할 게시글 조회
        BoardEntity boardEntity = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다: " + boardId));

        // 2. 현재 로그인한 사용자 정보 조회
        LoginEntity currentUser = loginRepository.findByUserid(currentUserId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다: " + currentUserId));

        // 3. 권한 확인
        if (!boardEntity.getLogin().getUserid().equals(currentUserId) &&
                !currentUser.getRole().name().equals("ADMIN")) {
            throw new AccessDeniedException("이 게시글을 삭제할 권한이 없습니다.");
        }

        // 4. 삭제
        boardRepository.delete(boardEntity);
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
