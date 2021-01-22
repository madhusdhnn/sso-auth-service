package com.thetechmaddy.authservice.services.session;

import com.thetechmaddy.authservice.models.User;
import org.springframework.session.Session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public interface UserSessionManager {

    String USER_ID_ATTRIBUTE_NAME = "userId";
    String COMPANY_ID_ATTRIBUTE_NAME = "companyId";
    String IDENTITY_ATTRIBUTE_NAME = "identity";


    HttpSession create(HttpServletRequest request, User user);

    default User getUserInSession(Session session) {
        return null;
    }

    default void invalidate(HttpSession httpSession) {
    }
}
