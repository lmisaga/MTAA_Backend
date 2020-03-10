package com.sclad.scladapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers(HttpMethod.POST,"/api/*").permitAll()
                .antMatchers(HttpMethod.GET,"/api/*").permitAll()
                .antMatchers(HttpMethod.PUT, "/api/*").permitAll()
                .antMatchers(HttpMethod.POST, "/api/user/*").permitAll()
                .antMatchers(HttpMethod.POST,"/api/*").permitAll()
                .anyRequest().authenticated();
    }
}
