package org.com.esti.web.controllers;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.codehaus.jackson.JsonGenerationException;
import org.com.esti.models.binding.ArtShowBindingModel;
import org.com.esti.models.binding.UserShowBindingModel;
import org.com.esti.models.binding.WatchShowBindingModel;
import org.com.esti.models.binding.WineShowBindingModel;
import org.com.esti.models.view.ArtViewModel;
import org.com.esti.models.view.UsersViewModel;
import org.com.esti.models.view.WatchViewModel;
import org.com.esti.models.view.WineViewModel;
import org.com.esti.service.ArtService;
import org.com.esti.service.UserPersonalService;
import org.com.esti.service.WatchService;
import org.com.esti.service.WineService;
import org.modelmapper.ModelMapper;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/department")
public class DepartmentController extends BaseController {
    private final WatchService watchService;
    private final WineService wineService;
    private final ModelMapper modelMapper;
    private final ArtService artService;
    private final UserPersonalService userPersonalService;

    public DepartmentController(WatchService watchService, WineService wineService, ModelMapper modelMapper, ArtService artService, UserPersonalService userPersonalService) {
        this.watchService = watchService;
        this.wineService = wineService;

        this.modelMapper = modelMapper;
        this.artService = artService;
        this.userPersonalService = userPersonalService;
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

    @GetMapping(value = "/users", produces = "application/json")
//     @ResponseBody
    public @ResponseBody
    ModelAndView allUsers(@ModelAttribute(name = "viewModel") UserShowBindingModel bindingModel) throws JsonGenerationException, JsonMappingException, IOException {
        List<UsersViewModel> usersViewModelList = this.userPersonalService.findAll().stream().map(u -> this.modelMapper.map(u, UsersViewModel.class)).collect(Collectors.toList());
        //   System.err.println(usersViewModelList);
        // GsonJsonParser gsonJsonParser = new GsonJsonParser();
        //  return usersViewModelList;
//MappingJackson2JsonView mappingJackson2JsonView = new MappingJackson2JsonView();
//mappingJackson2JsonView.

        //  return super.view(null,"departments/users",usersViewModelList);
        org.codehaus.jackson.map.ObjectMapper mapper = new org.codehaus.jackson.map.ObjectMapper();
        //mapper.writeValueAsString(usersViewModelList);
        //   System.out.println(mapper.writeValueAsString(usersViewModelList));
        return super.view("departments/users", usersViewModelList);
    }
}


//    @RequestMapping(value = "/", method = RequestMethod.GET)
//    public String home(Locale locale, Model model) throws JsonGenerationException, JsonMappingException, IOException {
//        logger.info("Welcome home! The client locale is {}.", locale);
//        LoadDataService dataService = new LoadDataService();
//        ObjectMapper mapper = new ObjectMapper();
//        model.addAttribute("employeeList", mapper.writeValueAsString(dataService.getEmployeeList()));
//        return "home";
//    }