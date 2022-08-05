package com.cts.microservice.main.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.cts.microservice.main.requestfilter.JwtRequestFilter;
import com.cts.microservice.main.service.UserValidationService;
import com.cts.microservice.main.util.JwtUtil;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	static Logger log = LogManager.getLogger(SecurityConfig.class);

	@Autowired
	private UserValidationService myUserDetailsService;
	
	@Autowired
    private JwtUnAuthorizedResponseAuthenticationEntryPoint jwtUnAuthorizedResponseAuthenticationEntryPoint;
	
	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	@Autowired
	private JwtUtil JwtFilter;

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		log.debug("AuthenticationManagerBuilder ");
		try {
			auth.userDetailsService(myUserDetailsService);
		} catch (Exception e) {
			throw new Exception("Empty UserDetails");
		}
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		log.debug("authenticationManagerBean()");
		return super.authenticationManagerBean();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		log.debug("passwordEncoder()");
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		log.debug(" HttpSecurity ");

		httpSecurity.csrf().disable()
				.authorizeRequests()
				.antMatchers("/jpa/checkuser/**", "/updateProfile", "/authenticate", "/register","/avatar/*","/background/*","/jpa/uploadBackground/*","/jpa/uploadAvatar/*")
				.permitAll().and().exceptionHandling()
				.authenticationEntryPoint(jwtUnAuthorizedResponseAuthenticationEntryPoint).and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests().anyRequest()
				.authenticated();

		httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

		httpSecurity.headers().frameOptions().sameOrigin() // H2 Console Needs this setting
				.cacheControl(); // disable caching
	}
	
	 @Override
	    public void configure(WebSecurity webSecurity) throws Exception {
	        webSecurity
	            .ignoring().antMatchers(HttpMethod.POST, "/register","/updateProfile","/jpa/checkuser/**","/jpa/uploadAvatar/*","/downloadFile/*","/jpa/uploadBackground/*").and()
	            .ignoring()
	            .antMatchers(
	                HttpMethod.POST,
	                 "/authenticate"
	            )
	            .antMatchers(HttpMethod.OPTIONS, "/**")
	            .and()
	            .ignoring()
	            .antMatchers(
	                HttpMethod.GET,
	                "/" 
	            )
	            .and()
	            .ignoring()
	            .antMatchers("/h2-console/**/**");
	    }
}
