package com.futsalground.portfolio.member.model;

import com.futsalground.portfolio.member.domain.Role;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberViewDto {

    private Long id;
    private String email;
    private Role membertype;

    private String addr1;
    private String addr2;

    private String position;

    private int boardcnt;
    private int boardreplycnt;

    private int recruitcnt;
    private int applycnt;
    private int revcnt;
    private int spend;


    public MemberViewDto(Long id, String email, Role membertype, String addr1, String addr2, String position, int boardcnt, int boardreplycnt, int recruitcnt, int applycnt, int revcnt, int spend) {
        this.id = id;
        this.email = email;
        this.membertype = membertype;
        this.addr1 = addr1;
        this.addr2 = addr2;
        this.position = position;
        this.boardcnt = boardcnt;
        this.boardreplycnt = boardreplycnt;
        this.recruitcnt = recruitcnt;
        this.applycnt = applycnt;
        this.revcnt = revcnt;
        this.spend = spend;
    }
}