package com.tejidos.presentation.controller;

import com.tejidos.presentation.dto.response.TypePaymentResponse;
import com.tejidos.service.TypePaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/types-payment")
public class TypePaymentController {

    private final TypePaymentService paymentService;

    public TypePaymentController(TypePaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping
    public ResponseEntity<List<TypePaymentResponse>> findAll(){
        return new ResponseEntity<>(paymentService.findAll(), HttpStatus.OK);
    }
}
