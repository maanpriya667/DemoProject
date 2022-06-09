package com.UserApplication.demo.security;

import com.UserApplication.demo.repository.DeviceDetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    private transient final org.springframework.core.env.Environment environment;

    @Autowired
    private DeviceDetRepository deviceDetRepository;

    @Autowired
    public WebSecurity(final Environment environment) {
        super();
        this.environment = environment;
    }


    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests().antMatchers(HttpMethod.OPTIONS).permitAll()

                .antMatchers(HttpMethod.POST, "/users/otp").permitAll()
                .antMatchers(HttpMethod.POST, "/users/verify").permitAll()
                .anyRequest().authenticated().and()
                .addFilter(new AuthFilter(authenticationManager(), environment,deviceDetRepository));
        http.headers().frameOptions().disable();


    }
}