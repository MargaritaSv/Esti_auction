package org.com.esti.web.controllers;

import org.springframework.web.servlet.ModelAndView;

public abstract class BaseController {
    public ModelAndView view(String viewName, ModelAndView modelAndView) {
        modelAndView.setViewName("fragments/layout");
        modelAndView.addObject("viewName", viewName);
        modelAndView.addObject("title", null);
        //modelAndView.setViewName(viewName);

        return modelAndView;
    }

    public ModelAndView view(String viewName) {
        return this.view(viewName, new ModelAndView());
    }

    public ModelAndView redirect(String url) {
        return this.view("redirect:" + url);
    }
}
