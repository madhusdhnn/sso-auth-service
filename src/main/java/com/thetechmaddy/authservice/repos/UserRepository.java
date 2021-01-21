package com.thetechmaddy.authservice.repos;

import com.thetechmaddy.authservice.domains.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<Employee, Long> {

    Optional<Employee> findByUsernameIgnoreCaseAndCompanyId(String username, String companyId);

}
