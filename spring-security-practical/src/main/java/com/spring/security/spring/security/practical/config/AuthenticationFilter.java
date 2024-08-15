package com.spring.security.spring.security.practical.config;

import com.spring.security.spring.security.practical.entity.BaseUser;
import com.spring.security.spring.security.practical.repository.BaseUserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
public class AuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    BaseUserRepository baseUserRepository;
    @Autowired
    JwtService jwtService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
       // 1 retrive header that contains jwt
        String authHeader = request.getHeader("Authorization"); // will retrive as <Bearer jwt>

        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request,response);
            return;
        }

        // 2 obtain jwt token
        String jwt = authHeader.split(" ")[1];

        // 3 obtain user name from that jwt
        String userName = jwtService.extractUserName(jwt);

        // 4 set authenticate object inside our security context
        BaseUser baseUser = baseUserRepository.findByUserName(userName).get();
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userName,null,baseUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        // 5 execute rest of filters as usual
        filterChain.doFilter(request,response);

    }
}
