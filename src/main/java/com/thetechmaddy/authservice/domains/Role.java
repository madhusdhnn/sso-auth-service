package com.thetechmaddy.authservice.domains;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "roles")
public class Role implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "role_id")
    private String roleId;

    @Column(name = "name")
    private String name;

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", roleId=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
