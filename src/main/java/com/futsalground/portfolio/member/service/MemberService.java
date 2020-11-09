package com.futsalground.portfolio.member.service;

import com.futsalground.portfolio.member.domain.Member;
import com.futsalground.portfolio.member.model.MemberSaveDto;
import com.futsalground.portfolio.member.model.MemberViewDto;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface MemberService {

    Long save(MemberSaveDto memberSaveDto);

    boolean checkDuplicateId(String email);

    Optional<MemberViewDto> findMember(Long id);

    Optional<Member> findByEmailAndPassword(String email, String password);
}
