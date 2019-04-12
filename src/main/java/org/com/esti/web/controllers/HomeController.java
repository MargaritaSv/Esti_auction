package org.com.esti.web.controllers;

import org.springframework.cglib.core.Local;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class HomeController extends BaseController {

    @GetMapping("/")
   //@PreAuthorize("isAuthenticated()")
    // @PreAuthorize("isAnonymous()")
    public ModelAndView home() {
        return super.view("index");
    }
}
