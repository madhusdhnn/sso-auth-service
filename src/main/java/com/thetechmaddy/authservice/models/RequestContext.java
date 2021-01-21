package com.thetechmaddy.authservice.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("request")
@Getter
@Setter
@ToString
public class RequestContext {

    private String companyId;

}
