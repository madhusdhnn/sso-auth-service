package com.thetechmaddy.authservice.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.thetechmaddy.authservice.domains.Employee;
import com.thetechmaddy.authservice.models.views.View;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Getter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
public class User implements UserDetails {

    @JsonView(value = View.Employee.class)
    @JsonProperty(value = "identity")
    private Employee employee;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return employee.getPassword();
    }

    @Override
    public String getUsername() {
        return employee.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return employee.isEnabled();
    }

    @Override
    public boolean isAccountNonLocked() {
        return employee.isEnabled();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return employee.isEnabled();
    }

    @Override
    public boolean isEnabled() {
        return employee.isEnabled();
    }

    public String getCompanyId() {
        return employee.getCompanyId();
    }
}
