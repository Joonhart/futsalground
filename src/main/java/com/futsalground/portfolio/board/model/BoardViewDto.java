package com.futsalground.portfolio.board.model;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class BoardViewDto {

    private Long id;
    private String writer;
    private String title;
    private String content;

    public BoardViewDto(Long id, String writer, String title, String content) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.content = content;
    }
}
