package com.futsalground.portfolio.board.domain;

import com.futsalground.portfolio.common.domain.BaseEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SequenceGenerator(
        name = "board_seq_generator",
        sequenceName = "board_seq", allocationSize = 1)
@ToString
@Table(name = "t_board")
public class Board extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "board_seq_generator")
    @Column(name = "id")
    private Long id;

    @NotEmpty
    @Column(name = "writer")
    private String writer;

    @NotEmpty
    @Column(name = "title")
    private String title;

    @NotEmpty
    @Column(name = "content")
    private String content;

    @Builder
    public Board(Long id, @NotEmpty String writer, @NotEmpty String title, @NotEmpty String content) {
        super(writer, LocalDateTime.now(), writer, LocalDateTime.now());
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.content = content;
    }


    public void update(String title, String content) {
        super.update("update", LocalDateTime.now());
        this.title = title;
        this.content = content;
    }
}
