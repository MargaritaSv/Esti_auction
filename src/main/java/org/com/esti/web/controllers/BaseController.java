package org.com.esti.web.controllers;

import org.springframework.cglib.core.Local;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

public abstract class BaseController {
    public ModelAndView view(Map<String, Local> map, String viewName, ModelAndView modelAndView) {
        modelAndView.setViewName("fragments/layout");
        modelAndView.addObject("viewName", viewName);
        modelAndView.addObject("title", null);

        return modelAndView;
    }

    public ModelAndView view(String viewName, ModelAndView modelAndView) {
        return this.view(null, viewName, modelAndView);

    }

    public ModelAndView view(String viewName) {
        return this.view(null, viewName, new ModelAndView());
    }

    public ModelAndView redirect(String url) {
        return this.view("redirect:" + url);
    }
}
