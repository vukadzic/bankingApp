package com.logate.banking.config;

import com.logate.banking.security.UnauthorizedAttemptExceptionHandler;
import com.logate.banking.security.UserDetailsImpl;
import com.logate.banking.security.jwt.JwtConfigurer;
import com.logate.banking.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfiguration  extends WebSecurityConfigurerAdapter {

    @Autowired
    private UnauthorizedAttemptExceptionHandler unauthorizedAttemptExceptionHandler;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserDetailsImpl userDetailsImpl;

    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception
    {
        auth
                .userDetailsService(userDetailsImpl)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable().headers().frameOptions().disable()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling().authenticationEntryPoint(unauthorizedAttemptExceptionHandler)
                .and()
                .authorizeRequests()
                    .antMatchers("/login").permitAll()
                    .antMatchers("/admin/**").hasRole("ADMIN")
                    .antMatchers("/user/**").hasAnyRole("ADMIN","USER")
                    .anyRequest().authenticated()
                .and()
                    .apply(securityConfigurerAdapter());
    }

    private JwtConfigurer securityConfigurerAdapter() {return new JwtConfigurer(jwtTokenProvider);}

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
