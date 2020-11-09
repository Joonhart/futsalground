package com.futsalground.portfolio.board.model;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
public class BoardViewDto {

    private Long id;
    private String writer;
    private String title;
    private String content;
    private LocalDateTime createdDate;

    public BoardViewDto(Long id, String writer, String title, String content, LocalDateTime createdDate) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
    }
}
