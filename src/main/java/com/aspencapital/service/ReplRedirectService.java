package com.aspencapital.service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class ReplRedirectService
{

    @RequestMapping("/")
    public RedirectView redirect() {
        return new RedirectView("swagger-ui.html");
    }

}
