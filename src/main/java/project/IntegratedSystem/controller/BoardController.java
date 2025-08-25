package project.IntegratedSystem.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.IntegratedSystem.dto.BoardDTO;
import project.IntegratedSystem.service.BoardService;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    // 게시글 목록
    @GetMapping("/list")
    public String boardList(Model model) {
        List<BoardDTO> boardList = boardService.getList();
        model.addAttribute("boardList", boardList);
        return "board/boardList";
    }

    // 게시글 상세 보기
    @GetMapping("/{id}")
    public String boardDetail(@PathVariable("id") Integer id, Model model) {
        BoardDTO board = boardService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid board Id:" + id));
        model.addAttribute("board", board);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && !"anonymousUser".equals(auth.getPrincipal())) {
            model.addAttribute("currentUserId", auth.getName());
        }

        return "board/boardDetail";
    }

    // 게시글 작성 폼
    @GetMapping("/add")
    public String createForm(Model model) {
        model.addAttribute("board", new BoardDTO());
        return "board/boardAdd";
    }

    // 게시글 작성 처리
    @PostMapping("/add")
    public String create(@ModelAttribute BoardDTO boardDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserId = authentication.getName();

        boardService.create(boardDTO, currentUserId);
        return "redirect:/board/list";
    }

    // 게시글 수정 폼
    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable("id") Integer id, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserId = authentication.getName();

        BoardDTO board = boardService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid board Id:" + id));

        if (!board.getWriterId().equals(currentUserId)) {
            throw new AccessDeniedException("이 게시글을 수정할 권한이 없습니다.");
        }

        model.addAttribute("board", board);
        return "board/boardUpdate";
    }

    // 게시글 수정 처리
    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") Integer id, @ModelAttribute BoardDTO boardDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserId = authentication.getName();

        boardService.update(id, boardDTO, currentUserId);
        return "redirect:/board/list";
    }

    // 게시글 삭제 처리
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserId = authentication.getName();

        boardService.delete(id, currentUserId);

        return "redirect:/board/list";
    }
}