package org.com.esti.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/private")
public class PrivateController extends BaseController {

    @GetMapping("/sell")
    public ModelAndView sell() {
        return super.view("private/private_sell");
    }

    @GetMapping("/buy")
    public ModelAndView buy() {
        return super.view("private/private_buy");
    }

    @GetMapping("/overview")
    public ModelAndView overview() {
        return super.view("private/overview");
    }
}
