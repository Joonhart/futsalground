package com.futsalground.portfolio.player.model;

import com.futsalground.portfolio.player.domain.Apply;
import com.futsalground.portfolio.player.domain.Recruit;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ApplyDto {

    private Long id;

    private String position;
    private String locate;
    private LocalDateTime wantdate;
    private String ages;
    private String skill;
    private String contactway;
    private String phone;
    private String explanation;

    public Apply toEntity(ApplyDto applyDto) {
        return Apply.builder()
                .id(id)
                .position(position)
                .locate(locate)
                .wantdate(wantdate)
                .ages(ages)
                .skill(skill)
                .contactway(contactway)
                .phone(phone)
                .explanation(explanation)
                .build();
    }

    public ApplyDto(Long id, String position, String locate, LocalDateTime wantdate, String ages, String skill, String contactway, String phone, String explanation) {
        this.id = id;
        this.position = position;
        this.locate = locate;
        this.wantdate = wantdate;
        this.ages = ages;
        this.skill = skill;
        this.contactway = contactway;
        this.phone = phone;
        this.explanation = explanation;
    }
}
