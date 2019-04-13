package org.com.esti.web.controllers;

import org.com.esti.domain.entities.User;
import org.com.esti.domain.entities.UserPersonal;
import org.com.esti.models.binding.ArtAddBindingModel;
import org.com.esti.models.service.ArtServiceModel;
import org.com.esti.service.ArtService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/service")
public class ServiceController extends BaseController {

    private final ArtService artService;
    private final ModelMapper modelMapper;

    @Autowired
    public ServiceController(ArtService artService, ModelMapper modelMapper) {
        this.artService = artService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/art")
    public ModelAndView addArt(@ModelAttribute("viewModel") ArtAddBindingModel bindingModel) {
        return super.view("services/add_art", bindingModel);
    }

    @PostMapping("/art")
    public ModelAndView addArtConfirm(@Valid @ModelAttribute("viewModel") ArtAddBindingModel bindingModel, BindingResult bindingResult,Authentication authentication) {
        UserPersonal userPersonal = ((User) authentication.getPrincipal()).getUserPersonal();

        if (bindingResult.hasErrors()) {
            return super.view("services/add_art", bindingModel);
        }

        bindingModel.setEstimatedBy(userPersonal);

        ArtServiceModel artServiceModel = this.artService.add(this.modelMapper.map(bindingModel, ArtServiceModel.class));
        if (artServiceModel == null) {
            throw new IllegalArgumentException("Something went wrong!");
        }
        return super.redirect("/");
    }
}
