package org.com.esti.web.controllers;

import org.com.esti.domain.entities.User;
import org.com.esti.domain.entities.UserPersonal;
import org.com.esti.models.binding.UserEditBindingModel;
import org.com.esti.models.service.UserPersonalServiceModel;
import org.com.esti.models.binding.UserRegisterBindingModel;
import org.com.esti.models.service.UserServiceModel;
import org.com.esti.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
    public ModelAndView editProfile(@ModelAttribute(name = "viewModel") UserEditBindingModel bindingModel, Authentication authentication) {
        UserPersonal userPersonal = ((User) authentication.getPrincipal()).getUserPersonal();
        //    System.out.println("--------> " + authentication);

        UserPersonalServiceModel personalServiceModel = this.modelMapper.map(userPersonal, UserPersonalServiceModel.class);
        return view("edit_user", personalServiceModel);
//        modelAndView.addObject("model",
//                this.modelMapper.map(this.userService.findUserByUserName(principal.getName()), UserProfileViewModel.class));
//
//        return super.view("edit_user", modelAndView);
    }

    @PostMapping("/edit")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView editProfileConfirm(@Validated @ModelAttribute(name = "viewModel") UserEditBindingModel bindingModel, BindingResult bindingResult, Authentication authentication) {
//        if (bindingModel.getPassword().equals(bindingModel.getConfirmPassword())) {
//            this.userService.editUserPassword(this.modelMapper.map(bindingModel, UserServiceModel.class), bindingModel.getOldPassword());
//
//            return super.redirect("/user/profile");
//        }
        if (bindingResult.hasErrors()) {
            return super.view("/edit_user", bindingModel);
        }

        UserPersonal userPersonal = ((User) authentication.getPrincipal()).getUserPersonal();

        this.userService.editUserPersonal(userPersonal.getId(), this.modelMapper.map(bindingModel, UserPersonalServiceModel.class));

        return super.redirect("/");

    }
}
