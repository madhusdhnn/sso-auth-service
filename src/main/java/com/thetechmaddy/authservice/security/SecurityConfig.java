package com.thetechmaddy.authservice.security;

import com.thetechmaddy.authservice.filters.RequestContextSettingFilter;
import com.thetechmaddy.authservice.security.handlers.LoginFailureHandler;
import com.thetechmaddy.authservice.security.handlers.LoginSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CrsAuthenticationEntryPoint crsAuthenticationEntryPoint;
    private final WebUserAuthenticationProvider webUserAuthenticationProvider;
    private final LoginSuccessHandler loginSuccessHandler;
    private final LoginFailureHandler loginFailureHandler;
    private final RequestContextSettingFilter requestContextSettingFilter;

    @Autowired
    public SecurityConfig(CrsAuthenticationEntryPoint crsAuthenticationEntryPoint,
                          WebUserAuthenticationProvider webUserAuthenticationProvider,
                          LoginSuccessHandler loginSuccessHandler, LoginFailureHandler loginFailureHandler,
                          RequestContextSettingFilter requestContextSettingFilter) {
        this.crsAuthenticationEntryPoint = crsAuthenticationEntryPoint;
        this.webUserAuthenticationProvider = webUserAuthenticationProvider;
        this.loginSuccessHandler = loginSuccessHandler;
        this.loginFailureHandler = loginFailureHandler;
        this.requestContextSettingFilter = requestContextSettingFilter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(this.webUserAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().disable()
                .csrf()
                .and()
                .cors()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(crsAuthenticationEntryPoint)
                .and()
                .addFilterBefore(this.requestContextSettingFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/internal/auth/initiate").permitAll()
                .antMatchers("/auth/login").permitAll()
                .antMatchers("/auth/authenticate").permitAll()
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
