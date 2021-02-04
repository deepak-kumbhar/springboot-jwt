package com.deepak.kumbhar.jwt.controller;

import com.deepak.kumbhar.jwt.model.*;
import com.deepak.kumbhar.jwt.service.*;
import com.deepak.kumbhar.jwt.utility.*;
import lombok.extern.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class HomeController {

    @Autowired
    private JwtUtility jwtUtility;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String home() {
        return "Welcome to home";
    }

    @PostMapping("/authenticate")
    public JwtResponse authenticateUser(@RequestBody JwtRequest jwtRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            jwtRequest.getUserName(),
                            jwtRequest.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            log.error(e.getMessage());
            throw new Exception("Invalid credentials ", e);
        }

        final UserDetails userDetails = userService.loadUserByUsername(jwtRequest.getUserName());
        return new JwtResponse(jwtUtility.generateToken(userDetails));

    }
}
