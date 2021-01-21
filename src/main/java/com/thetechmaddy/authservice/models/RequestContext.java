package com.thetechmaddy.authservice.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class RequestContext {

    private final String companyId;

}
