package com.futsalground.portfolio.board.model;

import com.futsalground.portfolio.common.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class BoardSaveDto {

    private Long id;
    @NotEmpty(message = "작성자 입력은 필수입니다.")
    private String writer;

    @NotEmpty(message = "제목을 입력해 주세요.")
    @Size(min = 3, max = 45)
    private String title;

    @NotEmpty(message = "내용을 입력해 주세요.")
    private String content;

}
