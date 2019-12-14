package project.demo.web.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import project.demo.service.BusService;
import project.demo.service.models.BusServiceModel;
import project.demo.service.models.CarServiceModel;
import project.demo.web.controller.base.BaseController;
import project.demo.web.models.SaleCreateBindingModel;
import project.demo.web.models.SearchCarBindingModel;

import java.util.List;

@Controller
@RequestMapping("/buses")
public class BusController extends BaseController {

    private BusService busService;

    private ModelMapper modelMapper;

    @Autowired
    public BusController(BusService busService, ModelMapper modelMapper) {
        this.busService = busService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/create")
    public ModelAndView createBus(){

        return super.view("buses/create-bus");
    }

    @PostMapping("/create")
    public ModelAndView createBusConfirm(@ModelAttribute SaleCreateBindingModel model){

        this.busService.publish(this.modelMapper.map(model, BusServiceModel.class));

        return super.redirect("/");
    }

    @GetMapping("/search")
    public ModelAndView listAllConfirm(){

        return super.view("buses/bus-search");
    }

    @PostMapping("/search")
    public ModelAndView searchCarsConfirm(@ModelAttribute SearchCarBindingModel model){

        List<BusServiceModel> allBy = this.busService.getAllByManufacturerAndModel(model.getManufacturer(),model.getModel());

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("buses/bus-search");
        modelAndView.addObject(allBy);
        return modelAndView;
    }

    @GetMapping("/show/{id}")
    public ModelAndView show(@PathVariable String id){

        ModelAndView modelAndView = new ModelAndView();

        BusServiceModel car = this.busService.getById(id);
        modelAndView.addObject(car);
        modelAndView.setViewName("buses/bus-show");

        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable String id){

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("buses/bus-edit");

        BusServiceModel carServiceModel = this.busService.getById(id);

        modelAndView.addObject(carServiceModel);

        return modelAndView;
    }

    @PostMapping("/edit/{id}")
    public ModelAndView modelAndView(@PathVariable String id,@ModelAttribute BusServiceModel model){


        this.busService.delete(this.busService.getById(id));

        this.busService.publish(model);

        return super.redirect("/");
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable String id){

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("buses/bus-delete");

        BusServiceModel carServiceModel = this.busService.getById(id);

        modelAndView.addObject(carServiceModel);

        return modelAndView;
    }

    @PostMapping("/delete/{id}")
    public ModelAndView deleteConfirm(@PathVariable String id){


        this.busService.delete(this.busService.getById(id));

        return super.redirect("/");
    }

}
