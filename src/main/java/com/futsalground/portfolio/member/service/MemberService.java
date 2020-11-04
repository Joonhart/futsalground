package com.futsalground.portfolio.member.service;

import com.futsalground.portfolio.member.domain.Member;
import com.futsalground.portfolio.member.model.MemberSaveDto;
import org.springframework.stereotype.Service;

public interface MemberService {

    void save(MemberSaveDto memberSaveDto);
}
