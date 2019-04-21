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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    @PreAuthorize("isAuthenticated()")
    public ModelAndView addArt(@ModelAttribute(name = "viewModel") ArtAddBindingModel bindingModel) {
        return this.view("services/add_art", new ArtAddBindingModel());
    }

    @PostMapping("/art")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView addArtConfirm(@Valid @ModelAttribute(name = "viewModel") ArtAddBindingModel bindingModel, BindingResult bindingResult, Authentication authentication, RedirectAttributes redirectAttributes) throws IOException {

        if (bindingResult.hasErrors()) {
            return this.view("services/add_art", bindingModel);
        }

        UserPersonal userPersonal = ((User) authentication.getPrincipal()).getUserPersonal();
        bindingModel.setEstimatedBy(userPersonal);

        ArtServiceModel artServiceModel = this.modelMapper.map(bindingModel, ArtServiceModel.class);
        artServiceModel.setImageUrl(this.cloudinarySErvice.uploadImages(bindingModel.getImageUrl()));

        artServiceModel = this.artService.add(artServiceModel);

        if (artServiceModel == null) {
            throw new IllegalArgumentException("Something went wrong!");
        }

        redirectAttributes.addFlashAttribute("success", "Canvas " + artServiceModel.getName() + " is saved.");
        return super.redirect("/department/canvas");
    }

    @GetMapping("/art/edit/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView aditArt(@PathVariable Long id) {
        ArtServiceModel artServiceModel = this.artService.findProductById(id);
        return this.view("services/edit_art", artServiceModel);
    }

    @PostMapping("/art/edit/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView aditArtConfirm(@PathVariable Long id, @ModelAttribute ArtAddBindingModel bindingModel, RedirectAttributes redirectAttributes) {
        this.artService.editProduct(id, this.modelMapper.map(bindingModel, ArtServiceModel.class));

        redirectAttributes.addFlashAttribute("success", "Canvas " + bindingModel.getName() + " is edit.");
        return super.redirect("/department/art");
    }

    @GetMapping("/art/delete/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView deleteArt(@PathVariable Long id, RedirectAttributes redirectAttributes) throws Exception {
        this.artService.deleteArt(id);
        redirectAttributes.addFlashAttribute("success", "Canvas is deleted.");
        return super.redirect("/department/art");
    }


    @GetMapping("/wine")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView addWine(@ModelAttribute("viewModel") WineAddBindingModel bindingModel) {
        return super.view("services/add_wine", bindingModel);
    }

    @PostMapping("/wine")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView addWineConfirm(@Valid @ModelAttribute("viewModel") WineAddBindingModel bindingModel, BindingResult bindingResult, Authentication authentication, RedirectAttributes redirectAttributes) throws IOException {

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

        redirectAttributes.addFlashAttribute("success", "Wine " + wineServiceModel.getName() + " is saved.");
        return super.redirect("/department/wines");
    }

    @GetMapping("/wine/edit/{id}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView aditWine(@PathVariable Long id) {
        WineServiceModel wineServiceModel = this.wineService.findProductById(id);
        return this.view("services/edit_wine", wineServiceModel);
    }

    @PostMapping("/wine/edit/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView aditWineConfirm(@PathVariable Long id, @ModelAttribute WineServiceModel bindingModel, RedirectAttributes redirectAttributes) {
        this.wineService.editWine(id, this.modelMapper.map(bindingModel, WineServiceModel.class));

        redirectAttributes.addFlashAttribute("success", "Wine " + bindingModel.getName() + " is changed.");
        return super.redirect("department/wines");
    }

    @GetMapping("/wine/delete/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView deleteWine(@PathVariable Long id, RedirectAttributes redirectAttributes) throws Exception {
        this.wineService.deleteWine(id);
        redirectAttributes.addFlashAttribute("success", "Wine is deleted.");
        return super.redirect("/department/wines");
    }


    @GetMapping("/watch")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView addArt(@ModelAttribute("viewModel") WatchAddBindingModel bindingModel) {
        return super.view("services/add_watch", bindingModel);
    }

    @PostMapping("/watch")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView addArtConfirm(@Valid @ModelAttribute("viewModel") WatchAddBindingModel bindingModel, BindingResult bindingResult, Authentication authentication, RedirectAttributes redirectAttributes) throws IOException {

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

        redirectAttributes.addFlashAttribute("success", "Watch " + watchtServiceModel.getName() + " is saved.");
        return super.redirect("/departments/watches");
    }

    @GetMapping("/watch/edit/{id}")
    public ModelAndView aditWatch(@PathVariable Long id) {
        WatchServiceModel watchServiceModel = this.watchService.findProductById(id);
        return this.view("services/edit_watch", watchServiceModel);
    }

    @PostMapping("/watch/edit/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView aditWatchConfirm(@PathVariable Long id, @Valid @ModelAttribute WatchAddBindingModel bindingModel, RedirectAttributes redirectAttributes) {
        this.watchService.editProduct(id, this.modelMapper.map(bindingModel, WatchServiceModel.class));

        redirectAttributes.addFlashAttribute("success", "Watch " + bindingModel.getName() + " is changed.");
        return super.redirect("/department/watches");
    }

    @GetMapping("/watch/delete/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView deleteWatch(@PathVariable Long id, RedirectAttributes redirectAttributes) throws Exception {
        this.watchService.deleteWatch(id);
        redirectAttributes.addFlashAttribute("success", "Watch is deleted.");
        return super.redirect("/department/watches");
    }
}
