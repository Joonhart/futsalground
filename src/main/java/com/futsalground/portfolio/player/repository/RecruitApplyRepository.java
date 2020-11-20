package com.futsalground.portfolio.player.repository;

import com.futsalground.portfolio.player.domain.ApplyMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecruitApplyRepository extends JpaRepository<ApplyMember, Long> {
}
