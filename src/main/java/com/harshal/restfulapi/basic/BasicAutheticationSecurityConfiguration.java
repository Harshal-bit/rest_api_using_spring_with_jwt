package com.harshal.restfulapi.basic;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


//@Configuration
public class BasicAutheticationSecurityConfiguration  {
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		return http
				.authorizeHttpRequests(
						auth -> auth.requestMatchers(new AntPathRequestMatcher("/**","OPTIONS"))
									.permitAll()
									.anyRequest().authenticated())
				.httpBasic(Customizer.withDefaults())
				.sessionManagement(
						session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.csrf(csrf -> csrf.disable())
				.build();
	}
}
