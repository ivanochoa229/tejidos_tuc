package com.tejidos.service.impl;

import com.tejidos.persistence.entity.TypePayment;
import com.tejidos.persistence.repository.TypePaymentRepository;
import com.tejidos.presentation.dto.response.TypePaymentResponse;
import com.tejidos.service.TypePaymentService;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TypePaymentServiceImpl implements TypePaymentService {

    private final TypePaymentRepository paymentRepository;

    public TypePaymentServiceImpl(TypePaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public List<TypePaymentResponse> findAll() {
        return paymentRepository.findAll().stream()
                .map(p -> new TypePaymentResponse(p.getTypePayment()))
                .collect(Collectors.toList());
    }

    @Override
    public TypePaymentResponse findById(Long idTypePayment) {
        Optional<TypePayment> optionalTypePayment = paymentRepository.findById(idTypePayment);
        if(optionalTypePayment.isEmpty()){
            throw new NotFoundException("TypePayment with id: " + idTypePayment + " was not found.");
        }
        return new TypePaymentResponse(optionalTypePayment.get().getTypePayment());
    }
}
