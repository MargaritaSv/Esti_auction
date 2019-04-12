package org.com.esti.web.controllers;

import org.com.esti.models.binding.UserEditBindingModel;
import org.com.esti.models.binding.UserLoginBindingModel;
import org.com.esti.models.view.UserProfileViewModel;
import org.com.esti.models.binding.UserRegisterBindingModel;
import org.com.esti.models.service.UserServiceModel;
import org.com.esti.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

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
        return super.view(null, "register", model);
    }

    @PostMapping("/register")
    @PreAuthorize("isAnonymous()")
    public ModelAndView registerConfirm(@ModelAttribute UserRegisterBindingModel bindingModel) {

        if (!bindingModel.getPassword().equals(bindingModel.getConfirmPassword())) {
            return super.view("/register", bindingModel);
        }

        this.userService.registerUser(this.modelMapper.map(bindingModel, UserServiceModel.class));

        return super.redirect("/user/login");
    }

    @GetMapping("/edit")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView editProfile(Principal principal, ModelAndView modelAndView) {
        modelAndView.addObject("model",
                this.modelMapper.map(this.userService.findUserByUserName(principal.getName()), UserProfileViewModel.class));

        return super.view("edit_user", modelAndView);
    }

    @PatchMapping("/edit")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView editProfileConfirm(@ModelAttribute UserEditBindingModel bindingModel) {
        if (bindingModel.getPassword().equals(bindingModel.getConfirmPassword())) {
            this.userService.editUserProfile(this.modelMapper.map(bindingModel, UserServiceModel.class), bindingModel.getOldPassword());

            return super.redirect("/user/profile");
        }

        return super.view("edit_user");

    }
}
