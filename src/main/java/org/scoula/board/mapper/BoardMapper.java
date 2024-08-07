package org.scoula.board.mapper;

import org.scoula.board.domain.BoardVO;

import java.util.List;

public interface BoardMapper {
    //    @Select("select * from tbl_board order by no desc")
    public List<BoardVO> getList();


    public List<BoardVO> getList2();

    public BoardVO get(Long no);

    public void create(BoardVO board);

}
