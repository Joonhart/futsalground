package com.futsalground.portfolio.player.model;

import com.futsalground.portfolio.member.domain.Member;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class RecruitPageViewDto {

    private Long id;
    private String groundname;
    private String addr1;
    private String addr2;
    private LocalDateTime starttime;
    private Member recruitMember;
    private int seledtedMembers;
    private int volume;
    private boolean isOpen;

    public RecruitPageViewDto(Long id, String groundname, String addr1, String addr2, LocalDateTime starttime, Member recruitMember, int seledtedMembers, int volume, boolean isOpen) {
        this.id = id;
        this.groundname = groundname;
        this.addr1 = addr1;
        this.addr2 = addr2;
        this.starttime = starttime;
        this.recruitMember = recruitMember;
        this.seledtedMembers = seledtedMembers;
        this.volume = volume;
        this.isOpen = isOpen;
    }
}
