package com.futsalground.portfolio.ground.repository;


import com.futsalground.portfolio.ground.domain.Reservation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class GroundRevCustomRepositoryImpl implements GroundRevCustomRepository {

    private final EntityManager em;

    @Override
    public List<String> findRevs(Long id, LocalDate date) {
        return em.createQuery("select r.revTime from Reservation r where r.ground.id = :id and r.revDate = :date", String.class)
                .setParameter("id", id)
                .setParameter("date", date)
                .getResultList();

    }
}
