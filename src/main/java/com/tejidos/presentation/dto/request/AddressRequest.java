package com.tejidos.presentation.dto.request;

public record AddressRequest(   Long number,
                                String province,
                                String state,
                                Long idClient) {
}
