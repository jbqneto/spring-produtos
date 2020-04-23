package io.jbqneto.produtos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import io.jbqneto.produtos.config.BasicAuthentication;

@EnableWebSecurity
public class SegurancaConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	BasicAuthentication basicAuth;

	@Autowired
	protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("jbqneto").password("{noop}3101").roles("USER");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests()
			.antMatchers(HttpMethod.GET, "/api/**").permitAll()
			.antMatchers("/api/**").authenticated()
			.and().cors();
		http.httpBasic().authenticationEntryPoint(basicAuth);
	}
	
}
