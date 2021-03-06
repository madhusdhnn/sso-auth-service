package com.thetechmaddy.authservice.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.Arrays;

@Getter
@ToString
@RequiredArgsConstructor
public enum RoleType {

    ADMIN("ROLE_ADMIN", "1"),
    MANAGER("ROLE_MANAGER", "2"),
    EMPLOYEE("ROLE_EMPLOYEE", "3");

    private final String name;
    private final String id;

    public static RoleType parse(String id) {
        return Arrays.stream(RoleType.values())
                .filter(roleType -> roleType.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("Un-supported role type id passed - %s", id)));
    }

}
