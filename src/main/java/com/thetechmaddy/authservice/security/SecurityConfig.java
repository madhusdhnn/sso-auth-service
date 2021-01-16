package com.thetechmaddy.authservice.security;

import com.thetechmaddy.authservice.security.handlers.LoginFailureHandler;
import com.thetechmaddy.authservice.security.handlers.LoginSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CrsAuthenticationProvider crsAuthenticationProvider;
    private final CrsAuthenticationEntryPoint crsAuthenticationEntryPoint;
    private final LoginSuccessHandler loginSuccessHandler;
    private final LoginFailureHandler loginFailureHandler;

    @Autowired
    public SecurityConfig(CrsAuthenticationProvider crsAuthenticationProvider,
                          CrsAuthenticationEntryPoint crsAuthenticationEntryPoint,
                          LoginSuccessHandler loginSuccessHandler, LoginFailureHandler loginFailureHandler) {
        this.crsAuthenticationProvider = crsAuthenticationProvider;
        this.crsAuthenticationEntryPoint = crsAuthenticationEntryPoint;
        this.loginSuccessHandler = loginSuccessHandler;
        this.loginFailureHandler = loginFailureHandler;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(this.crsAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().disable()
                .csrf()
                .and()
                .cors()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(crsAuthenticationEntryPoint)
                .and()
                .authorizeRequests()
                .antMatchers("/internal/auth/initiate").permitAll() //TODO: Remove after initial dev
                .antMatchers("/auth/login").permitAll()
                .antMatchers("/auth/authenticate").permitAll()
                .antMatchers("/auth/error").permitAll()
                .antMatchers("/ping").permitAll() // TODO: add health check controller from Utilities
                .antMatchers("/**/*.js").permitAll()
                .antMatchers("/**/*.css").permitAll()
                .antMatchers("/**/favicon.ico").permitAll()
                .antMatchers("/**/*.ttf").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/auth/login")
                .loginProcessingUrl("/auth/login")
                .permitAll()
                .successHandler(loginSuccessHandler)
                .failureHandler(loginFailureHandler)
                .and()
                .logout()
                .permitAll();
    }

}
