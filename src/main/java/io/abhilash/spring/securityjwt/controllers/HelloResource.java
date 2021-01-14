package io.abhilash.spring.securityjwt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.abhilash.spring.securityjwt.models.AuthenticationRequest;
import io.abhilash.spring.securityjwt.models.AuthenticationResponse;
import io.abhilash.spring.securityjwt.services.MyUserDetailsService;
import io.abhilash.spring.securityjwt.utils.JWTUtil;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class HelloResource {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private JWTUtil jwtUtil;

    @GetMapping("/hello")
    public String hello(){
        return "Hello! Login to see magic!";
    }

    //takes in the username and password from request and authenticates, if no error, it generates a jwt token and sends back in the response
    @PostMapping(value="authenticate")
    public ResponseEntity<?> createAuthTokenEntity(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
       
        // Authentication manager goes to the loadUserByUsername service with the username and password, if the user is present in db, returns the UserDetails Object otherwise throws an exception
       try{
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
        );
       } catch(BadCredentialsException e){
           throw new Exception("Incorrect username and password", e);
       }

       final UserDetails userDetails = myUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        
       final String jwt = jwtUtil.generateToken(userDetails);

       return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }  
    
    @GetMapping("/user")
    public String user(){
        return "Welcome user!";
    }

    @GetMapping("/admin")
    public String admin(){
        return "Welcome admin!";
    }
}
