package project.IntegratedSystem.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import project.IntegratedSystem.dto.BoardDTO;
import project.IntegratedSystem.service.BoardService;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/board/{id}")
    public String boardDetail(@PathVariable Integer id, Model model) {

        Optional<BoardDTO> boardOptional = boardService.findById(id);

        if (boardOptional.isPresent()) {
            BoardDTO board = boardOptional.get();
            model.addAttribute("board", board);
            return "/board/boardDetail";
        }
        else {
            return "redirect:/boardList";
        }
    }

    @GetMapping("/board/add")
    public String addForm(Model model) {
        model.addAttribute("board", new BoardDTO());

        return "/board/boardAdd";
    }

    @PostMapping("/board/add")
    public String addBoard(@ModelAttribute BoardDTO boardDTO) {
        boardService.save(boardDTO);

        return "redirect:/boardList";
    }

    @GetMapping("/board/update/{id}")
    public String updateForm(@PathVariable("id") Integer id, Model model) {

        Optional<BoardDTO> boardDTOOptional = boardService.findById(id);

        if (boardDTOOptional.isPresent()) {
            BoardDTO boardDTO = boardDTOOptional.get();
            model.addAttribute("board", boardDTO);

            return "/board/boardUpdate";
        }
        else {
            return "redirect:/boardList";
        }
    }

    @PostMapping("/board/update")
    public String update(@ModelAttribute BoardDTO boardDTO) {
        boardService.update(boardDTO);

        return "redirect:/board/" + boardDTO.getId();
    }
}
