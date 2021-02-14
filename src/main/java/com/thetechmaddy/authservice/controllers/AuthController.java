package com.thetechmaddy.authservice.controllers;

import com.thetechmaddy.authservice.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.thetechmaddy.authservice.utils.CryptUtils.aesDecrypt;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping(value = "/auth")
public class AuthController extends BaseController {

    private final String aesKey;
    private final String ssoDashboardUrl;
    private final AuthenticationService authenticationService;

    @Autowired
    public AuthController(@Value("${aes.secret}") String aesKey, @Value("${sso.dashboard.url}") String ssoDashboardUrl,
                          AuthenticationService authenticationService) {
        this.aesKey = aesKey;
        this.ssoDashboardUrl = ssoDashboardUrl;
        this.authenticationService = authenticationService;
    }

    @RequestMapping(value = "/login")
    public ModelAndView login(@RequestParam(value = "redirect") String redirectParam, ModelAndView modelAndView) {
        modelAndView.addObject("redirect", redirectParam);
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @RequestMapping(value = "/authenticate")
    public void authenticate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String redirectURLEncrypted = request.getParameter("redirect");
        String redirectURL = null;

        if (redirectURLEncrypted != null) {
            redirectURL = aesDecrypt(redirectURLEncrypted, aesKey);
        }

        authenticationService.authenticate(request);
        response.sendRedirect(redirectURL != null ? redirectURL : ssoDashboardUrl);
    }

    @RequestMapping(value = "/logout", method = GET)
    public void logout(HttpServletRequest request) {
        authenticationService.logout(request);
    }

}
