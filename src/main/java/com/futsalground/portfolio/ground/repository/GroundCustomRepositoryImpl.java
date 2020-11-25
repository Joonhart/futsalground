package com.futsalground.portfolio.ground.repository;

import com.futsalground.portfolio.ground.domain.Ground;
import com.futsalground.portfolio.ground.model.GroundSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;


@RequiredArgsConstructor
@Repository
public class GroundCustomRepositoryImpl implements GroundCustomRepository {

    private final EntityManager em;

    @Override
    public Page<Ground> findAllSearch(Pageable pageable, GroundSearch groundSearch) {
        List<Ground> grounds = em.createQuery("select g from Ground g where g.roadAddr like :addr1 and g.roadAddr like :addr2 and g.grdName like :grdName", Ground.class)
                .setParameter("addr1",  '%'+groundSearch.getAddr1()+'%')
                .setParameter("addr2", '%'+groundSearch.getAddr2()+'%')
                .setParameter("grdName", '%'+groundSearch.getGrdName()+'%')
                .getResultList();

        int start = (int)pageable.getOffset();
        int end = (start + pageable.getPageSize()) > grounds.size() ? grounds.size() : (start + pageable.getPageSize());
        return new PageImpl<>(grounds.subList(start, end), pageable, grounds.size());
    }
}
