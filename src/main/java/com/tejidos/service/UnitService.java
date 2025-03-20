package com.tejidos.service;

import com.tejidos.presentation.dto.response.UnitResponse;

import java.util.List;

public interface UnitService {
    List<UnitResponse> findAll();
    UnitResponse findById(Long idUnit);
}
