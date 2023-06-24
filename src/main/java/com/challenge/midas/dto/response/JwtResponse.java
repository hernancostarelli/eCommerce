package com.challenge.midas.dto.response;

import lombok.Data;

@Data
public class JwtResponse {

    private final String token;

    public JwtResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}