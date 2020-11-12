package com.futsalground.portfolio.member.service;

import com.futsalground.portfolio.member.domain.Member;
import com.futsalground.portfolio.member.model.MemberSaveDto;
import com.futsalground.portfolio.member.model.MemberViewDto;

import java.util.Optional;

public interface MemberService {

    Long save(MemberSaveDto memberSaveDto);

    boolean checkDuplicateId(String email);

    Optional<MemberViewDto> findMember(Long id);

    Optional<Member> findByEmailAndPassword(String email, String password);

    void update(String email, String addr1, String addr2, String position);

    void changePW(String email, String newPW);
}
