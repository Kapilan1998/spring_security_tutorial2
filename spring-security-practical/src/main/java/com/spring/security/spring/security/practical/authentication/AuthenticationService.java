package com.spring.security.spring.security.practical.authentication;

import com.spring.security.spring.security.practical.config.JwtService;
import com.spring.security.spring.security.practical.entity.BaseUser;
import com.spring.security.spring.security.practical.repository.BaseUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    BaseUserRepository baseUserRepository;
    @Autowired
    JwtService jwtService;

    public AuthenticationResponse login(AuthenticationRequest authenticationRequest) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                authenticationRequest.getUserName(), authenticationRequest.getPassword());

        authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        BaseUser baseUser = baseUserRepository.findByUserName(authenticationRequest.getUserName()).get();

        String jwt = jwtService.generateToken(baseUser,generateExtraClaims(baseUser));
        return new AuthenticationResponse(jwt);
    }

    private Map<String,Object> generateExtraClaims(BaseUser baseUser) {
        Map<String,Object> extraClaims = new HashMap<>();
        extraClaims.put("name",baseUser.getName());
        extraClaims.put("role",baseUser.getRole().name());

        return extraClaims;
    }
}
