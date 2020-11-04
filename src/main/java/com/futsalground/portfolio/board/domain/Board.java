package com.futsalground.portfolio.board.domain;

import com.futsalground.portfolio.common.domain.BaseEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SequenceGenerator(
        name = "board_seq_generator",
        sequenceName = "board_seq", allocationSize = 1)
@EqualsAndHashCode(of = "id")
@ToString
public class Board extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "board_seq_generator")
    private Long id;

    @NotEmpty
    private String writer;

    @NotEmpty
    private String title;

    @NotEmpty
    private String content;

    @Builder
    public Board(@NotEmpty String writer, @NotEmpty String title, @NotEmpty String content) {
        this.writer = writer;
        this.title = title;
        this.content = content;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
