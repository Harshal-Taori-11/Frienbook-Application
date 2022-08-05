package com.cts.microservice.adminreport.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtAuthCredentials{

	
	private String username;
	private String password;
	
}
