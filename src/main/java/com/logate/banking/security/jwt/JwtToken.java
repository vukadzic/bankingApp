package com.logate.banking.security.jwt;

public class JwtToken {

    private String token;

    public JwtToken(String token) {this.token = token;}

    public String getToken(){return token;}

}
