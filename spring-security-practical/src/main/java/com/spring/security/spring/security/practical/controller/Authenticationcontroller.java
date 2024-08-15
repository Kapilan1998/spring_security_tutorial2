package com.spring.security.spring.security.practical.controller;

import com.spring.security.spring.security.practical.authentication.AuthenticationRequest;
import com.spring.security.spring.security.practical.authentication.AuthenticationResponse;
import com.spring.security.spring.security.practical.authentication.AuthenticationService;
import com.spring.security.spring.security.practical.entity.BaseUser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class Authenticationcontroller  {

    private final AuthenticationService authenticationService;

    public Authenticationcontroller(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse>  register(@RequestBody BaseUser baseUser){
       return ResponseEntity.ok(authenticationService.register(baseUser));
    }

@PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate (@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(authenticationService.login(request));
}

//  1.08.30

}
