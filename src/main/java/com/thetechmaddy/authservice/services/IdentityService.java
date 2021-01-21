package com.thetechmaddy.authservice.services;

import com.thetechmaddy.authservice.domains.Employee;
import com.thetechmaddy.authservice.models.RequestContext;
import com.thetechmaddy.authservice.models.User;
import com.thetechmaddy.authservice.repos.UserRepository;
import com.thetechmaddy.authservice.utils.BeanUtil;
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
        RequestContext context = BeanUtil.getBean(RequestContext.class);
        String companyId = context.getCompanyId();
        return new User(loadUserByUsernameAndCompanyId(username, companyId));
    }

    public Employee loadUserByUsernameAndCompanyId(String username, String companyId) throws UsernameNotFoundException {
        return this.userRepository.findByUsernameIgnoreCaseAndCompanyId(username, companyId)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User not found with username - %s in company - %s", username, companyId)));
    }

}
