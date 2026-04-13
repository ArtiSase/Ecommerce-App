package com.aadiandjava.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	
	@Bean
	public BCryptPasswordEncoder encoder()
	{
		return new BCryptPasswordEncoder();
	}
	
	
	@Bean
	public UserDetailsService CreateUsers()
	{
		UserDetails user1=
		User.withUsername("arti")
		.password(encoder().encode("1212"))
		.roles("USER")
		.build();
		
		
		UserDetails user2=
				User.withUsername("pooja")
				.password(encoder().encode("1313"))
				.roles("ADMIN")
				.build();
		
		return new InMemoryUserDetailsManager(user1,user2);
	}
	
	
      //we have to define our own security logic
	
	public SecurityFilterChain securityLogic(HttpSecurity http)
	{
		http.authorizeHttpRequests(auth->{
		
//			auth.requestMatchers("/welcome").hasRole("USER");
//			auth.requestMatchers("/greet").hasRole("ADMIN");
			
			
			auth.anyRequest().authenticated();
			
		}).formLogin(form->form.permitAll());    //access form for everyone 
		
		return http.build();
	}
}
