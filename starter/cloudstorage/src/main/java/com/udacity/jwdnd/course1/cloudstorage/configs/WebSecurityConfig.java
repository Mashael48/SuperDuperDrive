package com.udacity.jwdnd.course1.cloudstorage.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.udacity.jwdnd.course1.cloudstorage.services.AuthenticationService;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	private final AuthenticationService authenticationService;

	/**
	 * Tell the application that we will use our `authenticationService` for
	 * the authentication
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) {
		auth.authenticationProvider(this.authenticationService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/signup", "/css/**", "/js/**")
			.permitAll()
			.anyRequest()
			.authenticated(); // Add paths that doesn't require login

		http.formLogin()
			.loginPage("/login")
			.permitAll(); // Provide our own login page

		http.formLogin()
			.defaultSuccessUrl("/home", true); // Set the direction page after login successfully
	}
}
