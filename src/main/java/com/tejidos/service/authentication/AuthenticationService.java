package com.tejidos.service.authentication;

import com.tejidos.persistence.entity.User;
import com.tejidos.presentation.dto.authentication.AuthenticationRequest;
import com.tejidos.presentation.dto.authentication.AuthenticationResponse;
import com.tejidos.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationService {

    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UserService userService, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    private Map<String, Object> generateExtraClaims(User user) {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("username",user.getUsername());
        extraClaims.put("role",user.getRole());

        return extraClaims;
    }

    public AuthenticationResponse login(AuthenticationRequest autRequest) {


        Authentication authentication = new UsernamePasswordAuthenticationToken(
                autRequest.username(), autRequest.password()
        );

        try{
            authenticationManager.authenticate(authentication);
        }catch (AuthenticationException ex){
            throw new BadCredentialsException("invalid username or password");
        }

        User user = userService.findByUsername(autRequest.username());
        String jwt = jwtService.generateToken(user, generateExtraClaims(user));

        return new AuthenticationResponse(jwt);
    }
}
