package org.scoula.board.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.scoula.board.domain.BoardAttachmentVO;
import org.scoula.board.domain.BoardVO;
import org.scoula.board.dto.BoardDTO;
import org.scoula.board.mapper.BoardMapper;
import org.scoula.common.util.UploadFiles;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.Optional;

@Log4j
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final static String BASE_DIR = "c:/upload/board";


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
    public BoardDTO get(Long no) { // no=44
        log.info("get......" + no);

        BoardVO boardVO = mapper.get(no);
        BoardDTO board = BoardDTO.of(boardVO); // BoardVO no=44

//        BoardDTO board2 = BoardDTO.of(mapper.get(no)); // BoardVO no=44

        return board;

//        return Optional.ofNullable(board)
//                .orElseThrow(NoSuchElementException::new); //
    }

//    @Override
//    public BoardDTO create(BoardDTO board) {
//        mapper.create(board.toVo());
//        log.info("board.toVo():" + board.getNo());
//        return board;
//    }

    // 2개 이상의 insert 문이 실행될 수 있으므로 트랜잭션 처리 필요
    // RuntimeException인 경우만 자동 rollback.
    @Transactional
    @Override
    public void create(BoardDTO board) {
        log.info("create......" + board);
        BoardVO boardVO = board.toVo();
        mapper.create(boardVO); // no == 47

        // 파일 업로드 처리
        List<MultipartFile> files = board.getFiles();
        if (files != null && !files.isEmpty()) { // 첨부 파일이 있는 경우
            upload(boardVO.getNo(), files);
        }
    }

    private void upload(Long bno, List<MultipartFile> files) { // 47, files
        for (MultipartFile part : files) {
            if (part.isEmpty()) continue;
            try {
                String uploadPath = UploadFiles.upload(BASE_DIR, part);
                BoardAttachmentVO attach = BoardAttachmentVO.of(part, bno, uploadPath);
                mapper.createAttachment(attach);
            } catch (IOException e) {
                throw new RuntimeException(e); // @Transactional에서 감지, 자동 rollback
            }
        }
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

    // 첨부파일 한 개 얻기
    @Override
    public BoardAttachmentVO getAttachment(Long no) {
        return mapper.getAttachment(no);
    }

    // 첨부파일 삭제
    @Override
    public boolean deleteAttachment(Long no) {
        return mapper.deleteAttachment(no) == 1;
    }
}
