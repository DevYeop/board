package org.scoula.board.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.scoula.board.dto.BoardDTO;
import org.scoula.board.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.Optional;

@Log4j
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    //    @Autowired
    final private BoardMapper mapper;

    @Override
    public List<BoardDTO> getList() {
        log.info("getList..........");

        List<BoardDTO> boardDTOList = mapper.getList().stream() // BoardVO의 스트림
                .map(BoardDTO::of) // BoardDTO의 스트림
                .toList(); // List<BoardDTO> 변환

        return boardDTOList;

//        return mapper.getList().stream() // BoardVO의 스트림
//                .map(BoardDTO::of) // BoardDTO의 스트림
//                .toList(); // List<BoardDTO> 변환
    }

    @Override
    public BoardDTO get(Long no) { // nb=44
        log.info("get......" + no);
        BoardDTO board = BoardDTO.of(mapper.get(no)); // no = 44
        return Optional.ofNullable(board)
                .orElseThrow(NoSuchElementException::new); //
    }

    @Override
    public BoardDTO create(BoardDTO board) {
        mapper.create(board.toVo());
        log.info("board.toVo():" + board.getNo());
        return board;
    }

    @Override
    public boolean update(BoardDTO board) {
        log.info("update......" + board);
        return mapper.update(board.toVo()) == 1;
    }

    @Override
    public boolean delete(Long no) {
        log.info("delete...." + no);
        return mapper.delete(no) == 1;
    }
}
