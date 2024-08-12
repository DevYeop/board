package org.scoula.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.scoula.board.dto.BoardDTO;
import org.scoula.board.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Log4j
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
    final private BoardService service;

    @GetMapping("/list")
    public void list(Model model) { // request : /board/list
        log.info("list");

        List<BoardDTO> boardDTOList = service.getList();
        model.addAttribute("listData", boardDTOList);

//        model.addAttribute("listData", service.getList());

//        return "/board/list";
    }

    @GetMapping("/create")
    public void create() {
        log.info("create");
    }

    @PostMapping("/create")
    public String create(BoardDTO board,
                         RedirectAttributes ra) {
        board = service.create(board);
        ra.addFlashAttribute("result", board.getNo());
        log.info("no:" + board.getNo());

//        return "board/list";
        return "redirect:/board/list";
    }

    @GetMapping({"/get", "/update"}) // board/get
    public void get(@RequestParam("no") Long no, Model model) { // no=44
        log.info("/get or update");

        BoardDTO boardDTO = service.get(no);
        model.addAttribute("board", boardDTO);

        // 위의 2줄을 줄여서 쓴 코드
//        model.addAttribute("board", service.get(no));

//        return "board/get";
    }

    @PostMapping("/update")
    public String update(BoardDTO board,
                         RedirectAttributes ra) {
        if (service.update(board)) {
            ra.addFlashAttribute("result", "success");
        }
        return "redirect:/board/list";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("no") Long no) {
        log.info("delete..." + no);
        service.delete(no);
        return "redirect:/board/list";
    }

}