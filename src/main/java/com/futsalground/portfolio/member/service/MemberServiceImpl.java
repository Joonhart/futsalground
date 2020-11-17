package com.futsalground.portfolio.member.service;

import com.futsalground.portfolio.member.domain.Member;
import com.futsalground.portfolio.member.model.MemberSaveDto;
import com.futsalground.portfolio.member.model.MemberViewDto;
import com.futsalground.portfolio.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public Long save(MemberSaveDto memberSaveDto) {
        Member member = Member.builder()
                .email(memberSaveDto.getEmail())
                .password(memberSaveDto.getPassword())
                .addr1(memberSaveDto.getAddr1())
                .addr2(memberSaveDto.getAddr2())
                .position(memberSaveDto.getPosition())
                .build();
        memberRepository.save(member);
        return member.getId();
    }

    @Override
    public boolean checkDuplicateId(String email) {
        Optional<Member> member = memberRepository.findByEmail(email);
        if (member.isEmpty()) {
            return true;
        }
        return false;
    }

    @Override
    public Optional<MemberViewDto> findMember(Long id) {
        Optional<MemberViewDto> findMember = memberRepository.findById(id).map(member -> new MemberViewDto(
                member.getId(),
                member.getEmail(),
                member.getMembertype(),
                member.getAddr1(),
                member.getAddr2(),
                member.getPosition(),
                member.getBoardcnt(),
                member.getBoardreplycnt(),
                member.getRecruitcnt(),
                member.getApplycnt(),
                member.getRevcnt(),
                member.getSpend()
        ));
        return findMember;
    }

    @Override
    public Optional<Member> findByEmailAndPassword(String email, String password) {
        Optional<Member> member = memberRepository.findByEmailAndPassword(email, password);
        return member;
    }

    @Override
    public void update(String email, String addr1, String addr2, String position) {
        Optional<Member> member = memberRepository.findByEmail(email);
        member.get().update(addr1, addr2, position);
        return;
    }

    @Override
    public void changePW(String email, String newPW) {
        Optional<Member> member = memberRepository.findByEmail(email);
        member.get().changePW(newPW);
        return;
    }
}
