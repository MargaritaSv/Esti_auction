package org.com.esti.web.controllers;

import org.com.esti.models.binding.ArtShowBindingModel;
import org.com.esti.models.binding.WatchShowBindingModel;
import org.com.esti.models.binding.WineShowBindingModel;
import org.com.esti.models.view.ArtViewModel;
import org.com.esti.models.view.WatchViewModel;
import org.com.esti.models.view.WineViewModel;
import org.com.esti.service.ArtService;
import org.com.esti.service.WatchService;
import org.com.esti.service.WineService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/department")
public class DepartmentController extends BaseController {
    private final WatchService watchService;
    private final WineService wineService;
    private final ModelMapper modelMapper;
    private final ArtService artService;

    public DepartmentController(WatchService watchService, WineService wineService, ModelMapper modelMapper, ArtService artService) {
        this.watchService = watchService;
        this.wineService = wineService;

        this.modelMapper = modelMapper;
        this.artService = artService;
    }

    @GetMapping("/art")
    public ModelAndView canvas(@ModelAttribute(name = "viewModel") ArtShowBindingModel bindingModel) {
        List<ArtViewModel> artViewModelList = this.artService.findAll().stream().map(a -> this.modelMapper.map(a, ArtViewModel.class)).collect(Collectors.toList());
        return super.view("departments/canvas", artViewModelList);
    }

    @GetMapping("/wines")
    public ModelAndView wines(@ModelAttribute(name = "viewModel") WineShowBindingModel bindingModel) {
        List<WineViewModel> wineViewModelList = this.wineService.findAll().stream().map(w -> this.modelMapper.map(w, WineViewModel.class)).collect(Collectors.toList());
        return super.view("departments/wines", wineViewModelList);
    }

    @GetMapping("/watches")
    public ModelAndView watches(@ModelAttribute(name = "viewModel") WatchShowBindingModel bindingModel) {
        List<WatchViewModel> watchViewModelList = this.watchService.findAll().stream().map(w -> this.modelMapper.map(w, WatchViewModel.class)).collect(Collectors.toList());
        return super.view("departments/watches", watchViewModelList);
    }
}
