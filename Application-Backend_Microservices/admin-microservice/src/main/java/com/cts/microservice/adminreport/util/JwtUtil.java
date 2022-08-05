package com.cts.microservice.adminreport.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.apache.log4j.Logger;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtUtil {

	static Logger log = Logger.getLogger(JwtUtil.class);
	private String SECRET_KEY = "HarshalT";

	public String generateToken(UserDetails userDetails) {
		log.debug("generateToken() --> UserDetails " + userDetails);
		Map<String, Object> claims = new HashMap<>();
		return createToken(claims, userDetails.getUsername());
	}

	private String createToken(Map<String, Object> claims, String subject) {
		log.debug("createToken() --> Subject " + subject);
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
				.signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
	}

	
	public String extractUsername(String token) {
		log.debug("extractUsername() --> Token " + token);
		return extractClaim(token, Claims::getSubject);
	}

	public Date extractExpiration(String token) {
		log.debug("extractExpiration() --> Token " + token);
		return extractClaim(token, Claims::getExpiration);
	}

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		log.debug("extractClaims() --> Token " + token + "and ClaimsResolver " + claimsResolver);
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {

		Claims c = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
		return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
	}

	// The isTokenExpired method returns if the token is expired or not
	private Boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	public Boolean validateToken(String token, UserDetails userDetails) {
		log.debug("validateToken() --> Token " + token + "and UserDetails" + userDetails);
		final String username = extractUsername(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

}
