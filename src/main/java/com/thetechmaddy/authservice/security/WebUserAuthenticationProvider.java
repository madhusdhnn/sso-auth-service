package com.thetechmaddy.authservice.security;

import com.thetechmaddy.authservice.domains.Employee;
import com.thetechmaddy.authservice.models.RequestContext;
import com.thetechmaddy.authservice.models.RequestContextHolder;
import com.thetechmaddy.authservice.models.User;
import com.thetechmaddy.authservice.services.IdentityService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class WebUserAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    private final IdentityService identityService;

    @Autowired
    public WebUserAuthenticationProvider(IdentityService identityService) {
        this.identityService = identityService;
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        if (authentication.getCredentials() == null) {
            log.error("Authentication failed: No credentials provided");
            throw new BadCredentialsException("Authentication failed: No credentials provided");
        }

        String presentedPassword = authentication.getCredentials().toString();
        if (!isPasswordValid(((User) userDetails), presentedPassword)) {
            log.error("Authentication failed: Password does not match");
            throw new BadCredentialsException("Authentication failed: Password does not match");
        }
    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        try {
            RequestContext context = RequestContextHolder.getContext();
            Employee employee = this.identityService.loadUserByUsernameAndCompanyId(username, context.getCompanyId());
            return new User(employee);
        } catch (Exception internalProblem) {
            throw new InternalAuthenticationServiceException(internalProblem.getMessage(), internalProblem);
        }
    }

    private boolean isPasswordValid(User user, String presentedPassword) {
        return new BCryptPasswordEncoder().matches(presentedPassword, user.getPassword());
    }

}
