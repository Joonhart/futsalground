package com.futsalground.portfolio.player.repository;

import com.futsalground.portfolio.player.domain.Recruit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecruitRepository extends JpaRepository<Recruit, Long> {

//    Page<Recruit> findByAddr1(String addr1, Pageable pageable);
//    Page<Recruit> findByGroundname(String groundname, Pageable pageable);
}
