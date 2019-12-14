package project.demo.web.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import project.demo.service.MotorcycleService;
import project.demo.service.TruckService;
import project.demo.service.models.CarServiceModel;
import project.demo.service.models.MotorcycleServiceModel;
import project.demo.service.models.TruckServiceModel;
import project.demo.web.controller.base.BaseController;
import project.demo.web.models.SaleCreateBindingModel;
import project.demo.web.models.SearchCarBindingModel;

import java.util.List;

@Controller
@RequestMapping("/trucks")
public class TruckController extends BaseController {

    private final TruckService truckService;

    private final ModelMapper modelMapper;

    @Autowired
    public TruckController(TruckService truckService, ModelMapper modelMapper) {
        this.truckService = truckService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/create")
    public ModelAndView create(){
        return super.view("trucks/create-truck");
    }

    @PostMapping("/create")
    public ModelAndView createConfirm(@ModelAttribute SaleCreateBindingModel model){

        this.truckService.publish(this.modelMapper.map(model, TruckServiceModel.class));

        return redirect("/");
    }

    @GetMapping("/search")
    public ModelAndView listAllConfirm(){

        return super.view("trucks/truck-search");
    }

    @PostMapping("/search")
    public ModelAndView searchCarsConfirm(@ModelAttribute SearchCarBindingModel model){

        List<TruckServiceModel> allBy = this.truckService.getAllByManufacturerAndModel(model.getManufacturer(),model.getModel());

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("trucks/truck-search");
        modelAndView.addObject(allBy);
        return modelAndView;
    }

    @GetMapping("/show/{id}")
    public ModelAndView show(@PathVariable String id){

        ModelAndView modelAndView = new ModelAndView();

        TruckServiceModel truck = this.truckService.getById(id);
        modelAndView.addObject(truck);
        modelAndView.setViewName("trucks/truck-show");

        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable String id){

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("trucks/truck-edit");

        TruckServiceModel truckServiceModel = this.truckService.getById(id);

        modelAndView.addObject(truckServiceModel);

        return modelAndView;
    }

    @PostMapping("/edit/{id}")
    public ModelAndView modelAndView(@PathVariable String id,@ModelAttribute TruckServiceModel model){


        this.truckService.delete(this.truckService.getById(id));

        this.truckService.publish(model);

        return super.redirect("/");
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable String id){

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("trucks/truck-delete");

        TruckServiceModel carServiceModel = this.truckService.getById(id);

        modelAndView.addObject(carServiceModel);

        return modelAndView;
    }

    @PostMapping("/delete/{id}")
    public ModelAndView deleteConfirm(@PathVariable String id){


        this.truckService.delete(this.truckService.getById(id));

        return super.redirect("/");
    }
}
