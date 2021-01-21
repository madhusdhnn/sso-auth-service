package com.thetechmaddy.authservice.controllers;

import com.thetechmaddy.authservice.utils.CryptUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/auth")
public class AuthController extends BaseController {

    private final String aesKey;
    private final String crsDashboardUrl;

    @Autowired
    public AuthController(@Value("${aes.secret}") String aesKey, @Value("${crs.dashboard.url}") String crsDashboardUrl) {
        this.aesKey = aesKey;
        this.crsDashboardUrl = crsDashboardUrl;
    }

    @RequestMapping(value = "/login")
    public ModelAndView login(@RequestParam(value = "redirect") String redirectParam, ModelAndView modelAndView,
                              HttpServletResponse response) {
        modelAndView.addObject("redirect", redirectParam);
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @RequestMapping(value = "/authenticate")
    public void authenticate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String redirectURLEncrypted = request.getParameter("redirect");
        String redirectURL = null;

        if (redirectURLEncrypted != null) {
            redirectURL = CryptUtils.aesDecrypt(redirectURLEncrypted, aesKey);
        }

        String accessToken = request.getParameter("access_token");
        System.out.printf("********* Access token: %s *********%n", accessToken);

        response.sendRedirect(redirectURL != null ? redirectURL : crsDashboardUrl);
    }

}
