package com.thetechmaddy.authservice.services.session;

import com.thetechmaddy.authservice.domains.Employee;
import com.thetechmaddy.authservice.exceptions.SessionNotFoundException;
import com.thetechmaddy.authservice.models.User;
import com.thetechmaddy.authservice.repos.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.session.Session;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Log4j2
@Service("webUserSessionManager")
public class WebUserSessionManager implements UserSessionManager {

    private final Integer maxInactiveIntervalInSec;
    private final UserRepository userRepository;

    @Autowired
    public WebUserSessionManager(@Value("${auth.maxInactiveInterval}") Integer maxInactiveInterval,
                                 UserRepository userRepository) {
        this.maxInactiveIntervalInSec = maxInactiveInterval;
        this.userRepository = userRepository;
    }

    @Override
    public HttpSession create(HttpServletRequest request, User user) {
        HttpSession session = request.getSession(true);
        session.setAttribute(USER_ID_ATTRIBUTE_NAME, user.getUsername());
        session.setAttribute(COMPANY_ID_ATTRIBUTE_NAME, user.getCompanyId());
        session.setMaxInactiveInterval(this.maxInactiveIntervalInSec);
        return session;
    }

    @Override
    public User getUserInSession(Session session) {
        String username = session.getAttribute(USER_ID_ATTRIBUTE_NAME);
        if (username == null) {
            throw new SessionNotFoundException("Invalid session");
        }
        String companyId = session.getAttribute(COMPANY_ID_ATTRIBUTE_NAME);
        Employee employee = userRepository.getByUsernameAndCompanyId(username, companyId);
        return new User(employee);
    }

    @Override
    public void invalidate(HttpSession httpSession) {
        if (httpSession != null) {
            httpSession.invalidate();
        }
    }

}
