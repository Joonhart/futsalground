package com.futsalground.portfolio.member.service;

import com.futsalground.portfolio.member.domain.Member;
import com.futsalground.portfolio.member.domain.Role;
import com.futsalground.portfolio.member.model.MemberSaveDto;
import com.futsalground.portfolio.member.model.MemberViewDto;
import com.futsalground.portfolio.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final HttpSession httpSession;

    @Override
    public Long save(MemberSaveDto memberSaveDto) {
        Member member = Member.builder()
                .email(memberSaveDto.getEmail())
                .password(memberSaveDto.getPassword())
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
                member.getEmail()
        ));
        return findMember;
    }

    @Override
    public Optional<Member> findByEmailAndPassword(String email, String password) {
        Optional<Member> member = memberRepository.findByEmailAndPassword(email, password);
        return member;
    }
}
