package com.spring.jwt.demo.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		// for JDBC authetication.
		// here I have loaded with default username and password. in actual, it will be loaded from db
		auth.jdbcAuthentication().dataSource(dataSource).withDefaultSchema()
				.withUser(User.withUsername("user").password("pass").roles("USER"))
				.withUser(User.withUsername("admin").password("pass").roles("ADMIN","USER"))
				.withUser(User.withUsername("test").password("test").roles("ADMIN","USER"));

		// FOR IN MEMROTY AUTH
		/*
		 * auth.inMemoryAuthentication().withUser("user")
		 * .password("password").roles("USER").and().withUser("admin").password(
		 * "admin").roles("ADMIN");
		 */
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/admin").hasRole("ADMIN").antMatchers("/user").hasAnyRole("USER", "ADMIN")
				.antMatchers("/").permitAll().and().csrf().ignoringAntMatchers("/h2-console/**","/h2/**")// don't
																								// apply
																								// CSRF
																								// protection
																								// to
																								// /h2-console
				.and().headers().frameOptions().sameOrigin().and().formLogin();// allow
																				// use
																				// of
																				// frame
																				// to
																				// same
																				// origin
																				// urls
	}

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		// PasswordEncoderFactories.createDelegatingPasswordEncoder();
		return NoOpPasswordEncoder.getInstance();
	}
}