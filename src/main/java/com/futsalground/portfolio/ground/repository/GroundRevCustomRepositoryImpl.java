package com.futsalground.portfolio.ground.repository;


import com.futsalground.portfolio.ground.domain.Reservation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class GroundRevCustomRepositoryImpl implements GroundRevCustomRepository {

    private final EntityManager em;

    @Override
    public List<String> findRevs(Long id, LocalDate date) {
        List<String> resultList = em.createQuery("select r.revTime from Reservation r where r.ground.id = :id and r.revDate = :date", String.class)
                .setParameter("id", id)
                .setParameter("date", date)
                .getResultList();

        ArrayList<String> result = new ArrayList<>();
        for (String s : resultList) {
            String[] s1 = s.split(" ");
            for (String s2 : s1) {
                s2.trim();
                if (!s2.equals("") && s2 != null) {
                    result.add(s2);
                }
            }
        }
        return result;

    }
}
