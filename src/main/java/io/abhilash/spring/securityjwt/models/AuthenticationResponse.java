package io.abhilash.spring.securityjwt.models;

public class AuthenticationResponse {
    private String jwt;

    public AuthenticationResponse(String jwt){
        this.jwt = jwt;
    }

    public String getJwt(){
        return this.jwt;
    }
}
