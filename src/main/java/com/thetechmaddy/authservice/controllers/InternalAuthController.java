package com.thetechmaddy.authservice.controllers;

import com.thetechmaddy.authservice.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import static com.thetechmaddy.authservice.utils.CryptUtils.aesEncrypt;

@Controller
@RequestMapping(value = "/internal/auth")
public class InternalAuthController extends BaseController {

    private final String aesKey;

    private final AuthenticationService authenticationService;

    @Autowired
    public InternalAuthController(@Value("${aes.secret}") String aesKey,
                                  AuthenticationService authenticationService) {
        this.aesKey = aesKey;
        this.authenticationService = authenticationService;
    }

    @GetMapping(value = "/initiate")
    public ModelAndView initiate(@RequestParam(value = "redirect_url") String redirectURL) {
        String encrypted = aesEncrypt(redirectURL, this.aesKey);
        System.out.println("Redirect" + redirectURL);
        ModelAndView modelAndView = new ModelAndView(new RedirectView(authenticationService.initiate()));
        modelAndView.addObject("redirect", encrypted);
        return modelAndView;
    }

}
