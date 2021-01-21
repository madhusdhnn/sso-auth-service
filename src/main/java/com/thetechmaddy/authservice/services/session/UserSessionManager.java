package com.thetechmaddy.authservice.services.session;

import com.thetechmaddy.authservice.models.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public interface UserSessionManager {

    String USER_ID_ATTRIBUTE_NAME = "userId";
    String COMPANY_ID_ATTRIBUTE_NAME = "customerId";


    HttpSession create(HttpServletRequest request, User user);

}
