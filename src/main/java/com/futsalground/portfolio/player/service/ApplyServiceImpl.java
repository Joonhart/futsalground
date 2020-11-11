package com.futsalground.portfolio.player.service;

import com.futsalground.portfolio.player.domain.Apply;
import com.futsalground.portfolio.player.model.ApplyDto;
import com.futsalground.portfolio.player.repository.ApplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ApplyServiceImpl implements ApplyService {

    private final ApplyRepository applyRepository;

    @Override
    public Optional<ApplyDto> findById(Long id) {
        return applyRepository.findById(id).map(apply -> new ApplyDto(
                apply.getId(),
                apply.getPosition(),
                apply.getLocate(),
                apply.getWantdate(),
                apply.getAges(),
                apply.getSkill(),
                apply.getContactway(),
                apply.getPhone(),
                apply.getExplanation()));
    }

    @Override
    public void create(ApplyDto applyDto) {
        applyRepository.save(applyDto.toEntity(applyDto));
    }

    @Override
    public Page<ApplyDto> findAll(Pageable pageable) {
        Page<Apply> applies = applyRepository.findAll(pageable);
        List<ApplyDto> applyDtos = pageToList(applies);
        return new PageImpl<>(applyDtos, pageable, applies.getTotalElements());
    }

    private List<ApplyDto> pageToList(Page<Apply> applies) {
        List<ApplyDto> applyDtos = applies.stream().map(apply -> new ApplyDto(
                apply.getId(),
                apply.getPosition(),
                apply.getLocate(),
                apply.getWantdate(),
                apply.getAges(),
                apply.getSkill(),
                apply.getContactway(),
                apply.getPhone(),
                apply.getExplanation()
        )).collect(Collectors.toList());
        return applyDtos;
    }
}
