package com.navinda.sequre.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;


@Configuration
public class AuthServerConfiguration extends WebSecurityConfigurerAdapter implements AuthorizationServerConfigurer{

	PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception{
		return super.authenticationManagerBean();
	}
	
	@Autowired
	AuthenticationManager authenticationManager;


    public void configure(ClientDetailsServiceConfigurer client) throws Exception {
    	client.inMemory().withClient("web").secret(passwordEncoder.encode("web"))
    	.authorizedGrantTypes("password").scopes("moblie");
    }
	
    public void configure(AuthorizationServerSecurityConfigurer sequrity) {
    	sequrity.checkTokenAccess("permitAll");
    }
    
    public void configure(AuthorizationServerEndpointsConfigurer endPoint) {
    	endPoint.authenticationManager(authenticationManager);
    }
	
}
