package com.tejidos.presentation.dto.response;

public record AddressResponse(  Long idAddress,
                                Long number,
                                String province,
                                String state,
                                String street) {
}
