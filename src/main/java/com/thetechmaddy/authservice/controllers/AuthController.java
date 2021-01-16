package com.thetechmaddy.authservice.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/auth")
public class AuthController extends BaseController {

    @RequestMapping(value = "/login")
    public ModelAndView login(@RequestParam(value = "redirect") String redirectParam, ModelAndView modelAndView, HttpServletResponse response) {
        modelAndView.addObject("redirect", redirectParam);
        modelAndView.setViewName("login");
        return modelAndView;
    }

}
