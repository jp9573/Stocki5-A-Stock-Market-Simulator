package com.csci5308.stocki5.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.csci5308.stocki5.user.UserAuthentication;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class Stocki5SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserAuthentication userAuthentication;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder authenticationMgr) throws Exception {
		authenticationMgr.authenticationProvider(userAuthentication);
	}

	@Bean
	public static NoOpPasswordEncoder passwordEncoder() {
		return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/login").permitAll().antMatchers("/resources").permitAll()
				.antMatchers("/stocks", "/orders", "/orders", "/prediction", "/profile").authenticated().and()
				.formLogin().loginPage("/login").defaultSuccessUrl("/stocks").failureUrl("/login?error")
				.usernameParameter("username").passwordParameter("password").and().logout().deleteCookies("JSESSIONID")
				.invalidateHttpSession(true).logoutSuccessUrl("/login?logout").permitAll().and().csrf().disable();

	}

}
