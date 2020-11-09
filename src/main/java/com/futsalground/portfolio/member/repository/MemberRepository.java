package com.futsalground.portfolio.member.repository;

import com.futsalground.portfolio.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmailAndPassword(String userEmail, String password);

    Optional<Member> findByEmail(String userEmail);
}
