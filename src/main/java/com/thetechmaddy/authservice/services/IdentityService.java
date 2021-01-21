package com.thetechmaddy.authservice.services;

import com.thetechmaddy.authservice.domains.Employee;
import com.thetechmaddy.authservice.models.RequestContextHolder;
import com.thetechmaddy.authservice.models.User;
import com.thetechmaddy.authservice.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class IdentityService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public IdentityService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String companyId = RequestContextHolder.getContext().getCompanyId();
        return new User(loadUserByUsernameAndCompanyId(username, companyId));
    }

    public Employee loadUserByUsernameAndCompanyId(String username, String companyId) throws UsernameNotFoundException {
        return this.userRepository.findByUsernameAndCompanyId(username, companyId)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User not found with username - %s in company - %s", username, companyId)));
    }

}
