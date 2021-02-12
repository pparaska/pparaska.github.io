package com.cg.shoppingcart.login.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class LoginSecurityConfig extends WebSecurityConfigurerAdapter {

	// Create 2 users for demo
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.inMemoryAuthentication().withUser("user").password("{noop}password").roles("USER").and().withUser("admin")
				.password("{noop}password").roles("USER", "ADMIN");

	}

	// Secure the endpoins with HTTP Basic authentication
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
				// HTTP Basic authentication
				.httpBasic().and().authorizeRequests().antMatchers(HttpMethod.GET, "/getAllProducts").hasRole("USER")
				.antMatchers(HttpMethod.GET, "/getProductById/**").hasRole("USER")
				.antMatchers(HttpMethod.POST, "/addNewProduct").hasRole("ADMIN")
				.antMatchers(HttpMethod.PUT, "/updateProduct/**").hasRole("ADMIN")
				.antMatchers(HttpMethod.DELETE, "/deleteProduct/**").hasRole("ADMIN")
				.antMatchers(HttpMethod.DELETE, "/deleteAllProduct/**").hasRole("ADMIN").and().csrf().disable()
				.formLogin().disable();
	}

}
