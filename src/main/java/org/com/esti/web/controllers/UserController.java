package org.com.esti.web.controllers;

import org.com.esti.models.binding.UserRegisterBindingModel;
import org.com.esti.models.service.UserServiceModel;
import org.com.esti.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("user")
public class UserController extends BaseController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/login")
    @PreAuthorize("isAnonymous()")
    public ModelAndView login() {
        return super.view("login");
    }

    @GetMapping("/register")
    @PreAuthorize("isAnonymous()")
    public ModelAndView register(@ModelAttribute(name = "viewModel") UserRegisterBindingModel model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(model);

        return super.view(null,"register", modelAndView);
    }

    @PostMapping("/register")
    @PreAuthorize("isAnonymous()")
    public ModelAndView registerConfirm(@ModelAttribute UserRegisterBindingModel model) {
        if (!model.getPassword().equals(model.getConfirmPassword())) {
            return super.view("register");
        }

        this.userService.registerUser(this.modelMapper.map(model, UserServiceModel.class));

        return super.redirect("/login");
    }
}
