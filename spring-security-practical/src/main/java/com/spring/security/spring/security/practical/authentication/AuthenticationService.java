package com.spring.security.spring.security.practical.authentication;

import com.spring.security.spring.security.practical.entity.BaseUser;
import com.spring.security.spring.security.practical.repository.BaseUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    BaseUserRepository baseUserRepository;

    public AuthenticationResponse login(AuthenticationRequest authenticationRequest) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                authenticationRequest.getUserName(), authenticationRequest.getPassword());

        authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        BaseUser baseUser = baseUserRepository.findByUserName(authenticationRequest.getUserName()).get();

        String jwt = jwtService.generateToken(baseUser,generateExtraClaims(baseUser));
        return new AuthenticationResponse(jwt);
    }
}
