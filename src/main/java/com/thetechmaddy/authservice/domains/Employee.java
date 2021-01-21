package com.thetechmaddy.authservice.domains;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.thetechmaddy.authservice.models.views.View;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "users")
public class Employee {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @JsonView(value = View.Employee.class)
    @Column(name = "company_id", nullable = false)
    private String companyId;

    @JsonView(value = View.Employee.class)
    @Column(name = "org_id", nullable = false)
    private String organizationId;

    @JsonView(value = View.Employee.class)
    @Column(name = "emp_id", nullable = false)
    private String employeeId;

    @JsonView(value = View.Employee.class)
    @Column(name = "user_name", unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @JsonView(value = View.Employee.class)
    @Column(name = "mobile_number", nullable = false)
    private String mobileNumber;

    @JsonView(value = View.Employee.class)
    @Column(name = "email_id", nullable = false)
    private String emailId;

    @JsonView(value = View.Employee.class)
    @Column(name = "enabled", nullable = false)
    private boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "emp_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @JsonView(value = View.Employee.class)
    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @JsonProperty(value = "roles")
    @JsonView(value = View.Employee.class)
    public List<String> getUserRoles() {
        return this.roles.stream().map(Role::getName).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", companyId='" + companyId + '\'' +
                ", organizationId='" + organizationId + '\'' +
                ", employeeId='" + employeeId + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", emailId='" + emailId + '\'' +
                ", enabled=" + enabled +
                ", createdAt=" + createdAt +
                '}';
    }
}
