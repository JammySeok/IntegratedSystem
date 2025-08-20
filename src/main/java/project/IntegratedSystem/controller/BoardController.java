package project.IntegratedSystem.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import project.IntegratedSystem.dto.BoardDTO;
import project.IntegratedSystem.service.BoardService;

import java.util.List;

@Controller
@AllArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/boardList")
    public String boardList(Model model) {
        List<BoardDTO> boardList = boardService.getList();
        model.addAttribute("boardList", boardList);

        return "/board/boardList";
    }

}
