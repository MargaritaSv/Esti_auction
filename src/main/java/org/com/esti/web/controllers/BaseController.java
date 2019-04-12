package org.com.esti.web.controllers;

import org.springframework.cglib.core.Local;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

public abstract class BaseController {
    //Map<String, Local> map,
    protected ModelAndView view(String delParameter, String viewName, Object object) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("viewModel", object);
        modelAndView.setViewName("fragments/layout");
        modelAndView.addObject("viewName", viewName);
        modelAndView.addObject("title", null);

        return modelAndView;
    }

    protected ModelAndView view(String viewName, Object object) {
        return this.view(null, viewName, object);
    }

    protected ModelAndView view(String viewName) {
        return this.view(null, viewName, new Object());
    }

    protected ModelAndView redirect(String url) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:" + url);

        return this.redirect(null, modelAndView);
    }

    private ModelAndView redirect(Map<String, Local> map, ModelAndView modelAndView) {
        return modelAndView;
    }
}
