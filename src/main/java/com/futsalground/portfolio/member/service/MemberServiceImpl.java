package com.futsalground.portfolio.member.service;

import com.futsalground.portfolio.member.domain.Member;
import com.futsalground.portfolio.member.domain.MemberType;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService, UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public void save(MemberSaveDto memberSaveDto) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Member member = Member.builder()
                .email(memberSaveDto.getEmail())
                .password(passwordEncoder.encode(memberSaveDto.getPassword()))
                .build();
        memberRepository.save(member);
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
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        Optional<Member> userEntityWrapper = memberRepository.findByEmail(userEmail);
        Member member = userEntityWrapper.get();

        List<GrantedAuthority> authorities = new ArrayList<>();

        if ((userEmail).startsWith("admin")) {
            authorities.add(new SimpleGrantedAuthority(MemberType.ADMIN.getValue()));
        } else {
            authorities.add(new SimpleGrantedAuthority(MemberType.MEMBER.getValue()));
        }

        return new User(member.getEmail(), member.getPassword(), authorities);
    }
}
