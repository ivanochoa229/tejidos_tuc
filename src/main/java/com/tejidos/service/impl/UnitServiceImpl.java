package com.tejidos.service.impl;

import com.tejidos.persistence.entity.Unit;
import com.tejidos.persistence.repository.UnitRepository;
import com.tejidos.presentation.dto.response.UnitResponse;
import com.tejidos.service.UnitService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UnitServiceImpl implements UnitService {

    private final UnitRepository unitRepository;

    public UnitServiceImpl(UnitRepository unitRepository) {
        this.unitRepository = unitRepository;
    }

    @Override
    public List<UnitResponse> findAll() {
        return unitRepository.findAll().stream().map(u -> new UnitResponse(u.getIdUnit(), u.getUnitName())).toList();
    }

    @Override
    public UnitResponse findById(Long idUnit) {
        Optional<Unit> optionalUnit = unitRepository.findById(idUnit);
        if(optionalUnit.isEmpty()){
            throw new RuntimeException();
        }
        return new UnitResponse(optionalUnit.get().getIdUnit(), optionalUnit.get().getUnitName());
    }
}
