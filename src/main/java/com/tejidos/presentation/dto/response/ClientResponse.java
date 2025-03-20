package com.tejidos.presentation.dto.response;

import java.util.List;

public record ClientResponse(Long idClient,
                             String name,
                             String lastname,
                             String dni,
                             String phone,
                             List<String> addresses) {
}
