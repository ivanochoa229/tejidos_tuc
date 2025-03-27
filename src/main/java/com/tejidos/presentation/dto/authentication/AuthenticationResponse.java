package com.tejidos.presentation.dto.authentication;

import java.io.Serializable;

public record AuthenticationResponse(String jwt) implements Serializable {
}
