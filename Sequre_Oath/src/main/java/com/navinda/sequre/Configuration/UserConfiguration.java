package com.navinda.sequre.Configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UserConfiguration extends GlobalAuthenticationConfigurerAdapter{

	PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
	@Override
	public void init(AuthenticationManagerBuilder auth) throws Exception {
	
		auth.inMemoryAuthentication()
		.withUser("navinda").password(passwordEncoder.encode("pass"))
		.roles("ADMIN","USER").authorities("CAN_READ","CAN_DELETE").and()
		.withUser("salaka").password(passwordEncoder.encode("pass"))
		.roles("USER").authorities("CAN_READ");
	}

	
}
