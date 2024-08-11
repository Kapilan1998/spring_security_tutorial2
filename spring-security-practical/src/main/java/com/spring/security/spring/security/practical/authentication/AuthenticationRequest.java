package com.spring.security.spring.security.practical.authentication;

import lombok.Data;

@Data
public class AuthenticationRequest {

    private String userName;
    private String password;

}
