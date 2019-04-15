package org.com.esti.web.controllers;

import org.com.esti.models.binding.ArtShowBindingModel;
import org.com.esti.models.view.ArtViewModel;
import org.com.esti.service.ArtService;
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
    // private final WatchService watchService;
    //  private final WineService wineService;
    private final ModelMapper modelMapper;
    private final ArtService artService;

    public DepartmentController(ModelMapper modelMapper, ArtService artService) {
        //  this.watchService = watchService;
        //   this.wineService = wineService;
        this.modelMapper = modelMapper;
        this.artService = artService;
    }

    @GetMapping("/art")
    public ModelAndView canvas(@ModelAttribute(name = "viewModel") ArtShowBindingModel bindingModel) {
        List<ArtViewModel> artViewModelList = this.artService.findAll().stream().map(a -> this.modelMapper.map(a, ArtViewModel.class)).collect(Collectors.toList());
        return super.view("departments/canvas", artViewModelList);
    }

//    @GetMapping("/watches")
//    public ModelAndView watches(Map<String, Local> map,
//                                @ModelAttribute(name = "bindingModel") WatchShowBindingModel bindingModel) {
//        List<WatchViewModel> watchViewModelList = this.watchService.findAll()
//                .stream()
//                .map(w -> this.modelMapper.map(w, WatchViewModel.class))
//                .collect(Collectors.toList());
//
//        //   modelAndView.addObject("watches", watchViewModelList);
//
//        return super.view("watches", watchViewModelList, null);
//    }
//
//
//    @GetMapping("/wines")
//    public ModelAndView wines(Map<String, Local> map, @ModelAttribute(name = "bindingModel") WineShowBindingModel bindingModel) {
//        List<WineViewModel> wineViewModelList = this.wineService.findAll()
//                .stream()
//                .map(wine -> this.modelMapper.map(wine, WineViewModel.class))
//                .collect(Collectors.toList());
//
//        return super.view("wines", wineViewModelList, "wines");
//    }
}
