package org.com.esti.web.controllers;

import org.com.esti.domain.entities.User;
import org.com.esti.domain.entities.UserPersonal;
import org.com.esti.models.binding.UserEditBindingModel;
import org.com.esti.models.binding.UserPasswordBindingModel;
import org.com.esti.models.service.UserPasswordServiceModel;
import org.com.esti.models.service.UserPersonalServiceModel;
import org.com.esti.models.binding.UserRegisterBindingModel;
import org.com.esti.models.service.UserServiceModel;
import org.com.esti.service.UserPersonalService;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("user")
public class UserController extends BaseController {

    private final UserService userService;
    private final UserPersonalService userPersonalService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserController(UserService userService, UserPersonalService userPersonalService, ModelMapper modelMapper) {
        this.userService = userService;
        this.userPersonalService = userPersonalService;
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
        return super.view("register", model);
    }

    @PostMapping("/register")
    @PreAuthorize("isAnonymous()")
    public ModelAndView registerConfirm(@Valid @ModelAttribute UserRegisterBindingModel bindingModel) {
        if (!bindingModel.getPassword().equals(bindingModel.getConfirmPassword())) {
            return super.view("/register", bindingModel);
        }

        this.userService.registerUser(this.modelMapper.map(bindingModel, UserServiceModel.class));

        return super.redirect("/user/login");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editOtherProfile(@PathVariable Long id, @ModelAttribute(name = "viewModel") UserEditBindingModel bindingModel, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return super.view("/edit_otherUser", bindingModel);
        }

        UserPersonalServiceModel personalServiceModel = this.modelMapper.map(id, UserPersonalServiceModel.class);
        if (personalServiceModel == null) {
            personalServiceModel = this.userService.findById(id);
        }
        return super.view("edit_otherUser", personalServiceModel);
    }

    @PostMapping("/edit/{id}")
    public ModelAndView editOtherProfileConfirm(@PathVariable Long id, @Valid @ModelAttribute(name = "viewModel") UserEditBindingModel bindingModel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return this.view("edit_user", bindingModel);
        }

        this.userService.editUserPersonal(id, this.modelMapper.map(bindingModel, UserPersonalServiceModel.class));

        return super.redirect("/");
    }

    @GetMapping("/edit")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView editProfile(@ModelAttribute(name = "viewModel") UserEditBindingModel bindingModel, BindingResult bindingResult, Authentication authentication) {
        if (bindingResult.hasErrors()) {
            return this.view("/edit_user", bindingModel);
        }

        UserPersonal userPersonal = ((User) authentication.getPrincipal()).getUserPersonal();

        UserPersonalServiceModel personalServiceModel = this.modelMapper.map(userPersonal, UserPersonalServiceModel.class);
        return this.view("edit_user", personalServiceModel);
    }

    @PostMapping("/edit")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView editProfileConfirm(@Validated @ModelAttribute(name = "viewModel") UserEditBindingModel bindingModel, BindingResult bindingResult, Authentication authentication) {
        if (bindingResult.hasErrors()) {
            return super.view("edit_user", bindingModel);
        }

        UserPersonal userPersonal = ((User) authentication.getPrincipal()).getUserPersonal();

        this.userService.editUserPersonal(userPersonal.getId(), this.modelMapper.map(bindingModel, UserPersonalServiceModel.class));

        return super.redirect("/");

    }

    @GetMapping("/password")
    public ModelAndView changePassword(@ModelAttribute(name = "viewModel") UserPasswordBindingModel bindingModel) {
        return this.view("password", bindingModel);
    }

    @PostMapping("/password")
    public ModelAndView changePassword(@ModelAttribute(name = "viewModel") UserPasswordBindingModel bindingModel, BindingResult bindingResult, Authentication authentication, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return this.view("password", bindingModel);
        }

        if (bindingModel.getNewPassword().equals(bindingModel.getConfirmNewPassword())) {
            User user = (User) authentication.getPrincipal();
            this.userService.editUserPassword(user.getId(), this.modelMapper.map(bindingModel, UserPasswordServiceModel.class));

            redirectAttributes.addFlashAttribute("success", "Password is changed.");
            return this.redirect("/");
        }

        return this.view("user/password");
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public void deleteWatch(@PathVariable Long id) {
        this.userPersonalService.deleteUserById(id);
    }
}