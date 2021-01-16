package com.thetechmaddy.authservice.security;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Log4j2
public class UserAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserAuthenticationProvider(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        if (authentication.getCredentials() == null) {
            log.error("Authentication failed: No credentials provided");
            throw new BadCredentialsException("Authentication failed: No credentials provided");
        }

        String presentedPassword = authentication.getCredentials().toString();
        if (!isPasswordValid(userDetails, presentedPassword)) {
            log.error("Authentication failed: Password does not match");
            throw new BadCredentialsException("Authentication failed: Password does not match");
        }
    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        try {
            return null;
        } catch (Exception internalProblem) {
            throw new InternalAuthenticationServiceException(internalProblem.getMessage(), internalProblem);
        }
    }

    //TODO: fetch password stored in db
    private boolean isPasswordValid(UserDetails userDetails, String presentedPassword) {
        return this.bCryptPasswordEncoder.matches(presentedPassword, userDetails.getPassword());
    }

}
