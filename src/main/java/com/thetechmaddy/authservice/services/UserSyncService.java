package com.thetechmaddy.authservice.services;

import com.thetechmaddy.authservice.domains.Employee;
import com.thetechmaddy.authservice.messaging.models.UserDetail;
import com.thetechmaddy.authservice.repos.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class UserSyncService {

    private final UserRepository userRepository;

    @Autowired
    public UserSyncService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public void syncUser(UserDetail userDetail) {
        if (!userDetail.validatePassword()) {
            log.error("Password is not encrypted for user - %s ");
            throw new PasswordNotEncryptedException("Password is not encrypted");
        }
        userRepository.save(new Employee(userDetail));
    }
}
