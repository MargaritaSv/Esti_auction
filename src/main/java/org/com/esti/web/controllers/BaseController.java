package org.com.esti.web.controllers;

import org.springframework.web.servlet.ModelAndView;

public abstract class BaseController {
    protected ModelAndView view(String viewName, Object object) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("fragments/layout");
        modelAndView.addObject("viewModel", object);
        modelAndView.addObject("viewName", viewName);
        modelAndView.addObject("title", null);

        return modelAndView;
    }

    protected ModelAndView view(String viewName) {

        return this.view(viewName, new Object());
    }

    protected ModelAndView redirect(String url) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:" + url);

        return modelAndView;
    }
}
