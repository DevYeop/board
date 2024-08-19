package org.scoula.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.scoula.board.dto.BoardDTO;
import org.scoula.board.service.BoardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
@Slf4j
public class BoardController {
    private final BoardService service;

//    @GetMapping("")
//    public List<BoardDTO> getList() {
//        return service.getList();
//    }

    @GetMapping("")
    public ResponseEntity<List<BoardDTO>> getList() {
        return ResponseEntity.ok(service.getList());
    }

    @GetMapping("/{no}")
    public ResponseEntity<BoardDTO> get(@PathVariable Long no) {
        return ResponseEntity.ok(service.get(no));
    }

    @PostMapping("")
    public ResponseEntity<BoardDTO> create(@RequestBody BoardDTO board) {
        return ResponseEntity.ok(service.create(board));
    }

    //    @PutMapping("/{id}")
    @PutMapping("/{no}")
//    public ResponseEntity<BoardDTO> update(@RequestBody BoardDTO board) {
    public ResponseEntity<BoardDTO> update(@PathVariable Long no, @RequestBody BoardDTO board) {
//        log.info("Updating board with id {}", id);
//        board.setNo(id);
        board.setNo(no);
        return ResponseEntity.ok(service.update(board));
    }

    @DeleteMapping("/{no}")
    // /api/board/44
//    public ResponseEntity<BoardDTO> delete(@PathVariable int id) {
    public ResponseEntity<BoardDTO> delete(@PathVariable Long no) {
        int number = 1;
        Long number2 = null;
        // int Integer Long

        return ResponseEntity.ok(service.delete(no));
    }
}