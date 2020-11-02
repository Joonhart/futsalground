package com.futsalground.portfolio;

import com.futsalground.portfolio.board.domain.Board;
import com.futsalground.portfolio.board.model.BoardViewDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Test {

    public static void main(String[] args) {

        List<Board> boardList = new ArrayList<>();
        Board board1 = new Board("writer", "title", "content");
        Board board2 = new Board("write2r", "ti2tle", "cont2ent");

        boardList.add(board1);
        boardList.add(board2);

        List<BoardViewDto> result = boardList.stream().map(b -> new BoardViewDto(
                1L,
                b.getWriter(),
                b.getTitle(),
                b.getContent()
        )).collect(Collectors.toList());

        System.out.println("result.size() = " + result.size());
    }
}
