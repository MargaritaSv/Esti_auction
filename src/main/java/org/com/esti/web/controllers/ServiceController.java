package org.com.esti.web.controllers;

import org.com.esti.domain.entities.User;
import org.com.esti.domain.entities.UserPersonal;
import org.com.esti.models.binding.ArtAddBindingModel;
import org.com.esti.models.service.ArtServiceModel;
import org.com.esti.service.ArtService;
import org.com.esti.service.CloudinaryService;
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
import java.io.IOException;

@Controller
@RequestMapping("/service")
public class ServiceController extends BaseController {

    private final ArtService artService;
    private final ModelMapper modelMapper;
    private final CloudinaryService cloudinarySErvice;

    @Autowired
    public ServiceController(ArtService artService, ModelMapper modelMapper, CloudinaryService cloudinarySErvice) {
        this.artService = artService;
        this.modelMapper = modelMapper;
        this.cloudinarySErvice = cloudinarySErvice;
    }

    @GetMapping("/art")
    public ModelAndView addArt(@ModelAttribute("viewModel") ArtAddBindingModel bindingModel) {
        return super.view("services/add_art", bindingModel);
    }

    @PostMapping("/art")
    public ModelAndView addArtConfirm(@Valid @ModelAttribute("viewModel") ArtAddBindingModel bindingModel, BindingResult bindingResult, Authentication authentication) throws IOException {

        if (bindingResult.hasErrors()) {
            return super.view("services/add_art", bindingModel);
        }

        UserPersonal userPersonal = ((User) authentication.getPrincipal()).getUserPersonal();
        bindingModel.setEstimatedBy(userPersonal);

        ArtServiceModel artServiceModel = new ArtServiceModel();
        artServiceModel.setImageUrl(this.cloudinarySErvice.uploadImages(bindingModel.getImageUrl()));
        artServiceModel = this.artService.add(this.modelMapper.map(bindingModel, ArtServiceModel.class));

        if (artServiceModel == null) {
            throw new IllegalArgumentException("Something went wrong!");
        }
        return super.redirect("/");
    }
}
