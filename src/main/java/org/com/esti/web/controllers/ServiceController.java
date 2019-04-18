package org.com.esti.web.controllers;

import org.com.esti.domain.entities.User;
import org.com.esti.domain.entities.UserPersonal;
import org.com.esti.models.binding.ArtAddBindingModel;
import org.com.esti.models.binding.WatchAddBindingModel;
import org.com.esti.models.binding.WineAddBindingModel;
import org.com.esti.models.service.ArtServiceModel;
import org.com.esti.models.service.WatchServiceModel;
import org.com.esti.models.service.WineServiceModel;
import org.com.esti.service.ArtService;
import org.com.esti.service.CloudinaryService;
import org.com.esti.service.WatchService;
import org.com.esti.service.WineService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping("/service")
public class ServiceController extends BaseController {

    private final ArtService artService;
    private final WineService wineService;
    private final WatchService watchService;
    private final ModelMapper modelMapper;
    private final CloudinaryService cloudinarySErvice;

    @Autowired
    public ServiceController(ArtService artService, WineService wineService, WatchService watchService, ModelMapper modelMapper, CloudinaryService cloudinarySErvice) {
        this.artService = artService;
        this.wineService = wineService;
        this.watchService = watchService;
        this.modelMapper = modelMapper;
        this.cloudinarySErvice = cloudinarySErvice;
    }

    @GetMapping("/art")
    public ModelAndView addArt(@ModelAttribute("viewModel") ArtAddBindingModel bindingModel) {
        return super.view("services/add_art", bindingModel);
    }

    @PostMapping("/art")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView addArtConfirm(@Valid @ModelAttribute("viewModel") ArtAddBindingModel bindingModel, BindingResult bindingResult, Authentication authentication) throws IOException {

        if (bindingResult.hasErrors()) {
            return super.view("services/add_art", bindingModel);
        }

        UserPersonal userPersonal = ((User) authentication.getPrincipal()).getUserPersonal();
        bindingModel.setEstimatedBy(userPersonal);

        ArtServiceModel artServiceModel = this.modelMapper.map(bindingModel, ArtServiceModel.class);
        artServiceModel.setImageUrl(this.cloudinarySErvice.uploadImages(bindingModel.getImageUrl()));

        artServiceModel = this.artService.add(artServiceModel);

        if (artServiceModel == null) {
            throw new IllegalArgumentException("Something went wrong!");
        }

        //TODO: msg save success flush
        // return super.view("services/add_art", bindingModel);
        return super.redirect("/");
    }

    @GetMapping("/art/edit/{id}")
    public ModelAndView aditArt(@PathVariable Long id) {
        ArtServiceModel artServiceModel = this.artService.findProductById(id);
        return this.view("services/edit_art", artServiceModel);
    }

    @PostMapping("/art/edit/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView aditArtConfirm(@PathVariable Long id, @ModelAttribute ArtAddBindingModel bindingModel) {
        this.artService.editProduct(id, this.modelMapper.map(bindingModel, ArtServiceModel.class));

//    TODO: redirect to  -> return super.redirect("/art/details/" + id);
        return super.redirect("/");
    }

    @GetMapping("/art/delete/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView deleteArt(@PathVariable Long id) throws Exception {
        this.artService.deleteArt(id);
        return super.redirect("/department/art");
    }


    @GetMapping("/wine")
    public ModelAndView addWine(@ModelAttribute("viewModel") WineAddBindingModel bindingModel) {
        return super.view("services/add_wine", bindingModel);
    }

    @PostMapping("/wine")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView addWineConfirm(@Valid @ModelAttribute("viewModel") WineAddBindingModel bindingModel, BindingResult bindingResult, Authentication authentication) throws IOException {

        if (bindingResult.hasErrors()) {
            return super.view("services/add_wine", bindingModel);
        }

        UserPersonal userPersonal = ((User) authentication.getPrincipal()).getUserPersonal();
        bindingModel.setEstimatedBy(userPersonal);

        WineServiceModel wineServiceModel = this.modelMapper.map(bindingModel, WineServiceModel.class);
        //tr da e v service-a
        wineServiceModel.setImageUrl(this.cloudinarySErvice.uploadImages(bindingModel.getImageUrl()));

        wineServiceModel = this.wineService.add(wineServiceModel);

        if (wineServiceModel == null) {
            throw new IllegalArgumentException("Something went wrong!");
        }

        //TODO: msg save success flush
        // return super.view("services/add_art", bindingModel);
        return super.redirect("/");
    }

    @GetMapping("/wine/edit/{id}")
    public ModelAndView aditWine(@PathVariable Long id) {
        WineServiceModel wineServiceModel = this.wineService.findProductById(id);
        return this.view("services/edit_wine", wineServiceModel);
    }

    @PostMapping("/wine/edit/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView aditWineConfirm(@PathVariable Long id, @ModelAttribute WineServiceModel bindingModel) {
        this.wineService.editWine(id, this.modelMapper.map(bindingModel, WineServiceModel.class));

//    TODO: redirect to  -> return super.redirect("/art/details/" + id);
        return super.redirect("/");
    }

    @GetMapping("/wine/delete/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView deleteWine(@PathVariable Long id) throws Exception {
        this.wineService.deleteWine(id);
        return super.redirect("/department/wine");
    }


    @GetMapping("/watch")
    public ModelAndView addArt(@ModelAttribute("viewModel") WatchAddBindingModel bindingModel) {
        return super.view("services/add_watch", bindingModel);
    }

    @PostMapping("/watch")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView addArtConfirm(@Valid @ModelAttribute("viewModel") WatchAddBindingModel bindingModel, BindingResult bindingResult, Authentication authentication) throws IOException {

        if (bindingResult.hasErrors()) {
            return super.view("services/add_watch", bindingModel);
        }

        UserPersonal userPersonal = ((User) authentication.getPrincipal()).getUserPersonal();
        bindingModel.setEstimatedBy(userPersonal);

        WatchServiceModel watchtServiceModel = this.modelMapper.map(bindingModel, WatchServiceModel.class);
        watchtServiceModel.setImageUrl(this.cloudinarySErvice.uploadImages(bindingModel.getImageUrl()));

        watchtServiceModel = this.watchService.add(watchtServiceModel);

        if (watchtServiceModel == null) {
            throw new IllegalArgumentException("Something went wrong!");
        }

        //TODO: msg save success flush
        // return super.view("services/add_art", bindingModel);
        return super.redirect("/");
    }

    @GetMapping("/watch/edit/{id}")
    public ModelAndView aditWatch(@PathVariable Long id) {
        WatchServiceModel watchServiceModel = this.watchService.findProductById(id);
        return this.view("services/edit_watch", watchServiceModel);
    }

    @PostMapping("/watch/edit/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView aditWatchConfirm(@PathVariable Long id, @ModelAttribute WatchAddBindingModel bindingModel) {
        this.watchService.editProduct(id, this.modelMapper.map(bindingModel, WatchServiceModel.class));

//    TODO: redirect to  -> return super.redirect("/art/details/" + id);
        return super.redirect("/");
    }

    @GetMapping("/watch/delete/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView deleteWatch(@PathVariable Long id) throws Exception {
        this.watchService.deleteWatch(id);
        return super.redirect("/department/watch");
    }


}
