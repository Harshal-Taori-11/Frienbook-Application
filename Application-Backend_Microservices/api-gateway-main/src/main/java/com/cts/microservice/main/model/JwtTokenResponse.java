package com.cts.microservice.main.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class JwtTokenResponse implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final String token;

    public JwtTokenResponse(String token) {
        this.token = token;
    }

   
}
