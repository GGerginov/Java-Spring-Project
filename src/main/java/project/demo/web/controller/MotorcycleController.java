package project.demo.web.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import project.demo.domain.entities.Motorcycle;
import project.demo.service.MotorcycleService;
import project.demo.service.models.CarServiceModel;
import project.demo.service.models.MotorcycleServiceModel;
import project.demo.web.controller.base.BaseController;
import project.demo.web.models.SaleCreateBindingModel;
import project.demo.web.models.SearchCarBindingModel;

import java.util.List;

@Controller
@RequestMapping("/motorcycles")
public class MotorcycleController extends BaseController {

    private final MotorcycleService motorcycleService;

    private final ModelMapper modelMapper;

    @Autowired
    public MotorcycleController(MotorcycleService motorcycleService, ModelMapper modelMapper) {
        this.motorcycleService = motorcycleService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/create")
    public ModelAndView create(){
        return super.view("motorcycles/create-motorcycle");
    }

    @PostMapping("/create")
    public ModelAndView createConfirm(@ModelAttribute SaleCreateBindingModel model){

        this.motorcycleService.publish(this.modelMapper.map(model, MotorcycleServiceModel.class));

        return redirect("/");
    }

    @GetMapping("/search")
    public ModelAndView listAllConfirm(){

        return super.view("motorcycles/moto-search");
    }

    @PostMapping("/search")
    public ModelAndView searchCarsConfirm(@ModelAttribute SearchCarBindingModel model){

        List<MotorcycleServiceModel> allBy = this.motorcycleService.getAllByManufacturerAndModel(model.getManufacturer(),model.getModel());

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("motorcycles/moto-search");
        modelAndView.addObject(allBy);
        return modelAndView;
    }

    @GetMapping("/show/{id}")
    public ModelAndView show(@PathVariable String id){

        ModelAndView modelAndView = new ModelAndView();

        MotorcycleServiceModel moto = this.motorcycleService.getById(id);
        modelAndView.addObject(moto);
        modelAndView.setViewName("motorcycles/moto-show");

        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable String id){

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("motorcycles/moto-edit");

        MotorcycleServiceModel moto = this.motorcycleService.getById(id);

        modelAndView.addObject(moto);

        return modelAndView;
    }

    @PostMapping("/edit/{id}")
    public ModelAndView modelAndView(@PathVariable String id,@ModelAttribute MotorcycleServiceModel model){


        this.motorcycleService.delete(this.motorcycleService.getById(id));

        this.motorcycleService.publish(model);

        return super.redirect("/");
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable String id){

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("motorcycles/moto-delete");

        MotorcycleServiceModel motorcycleServiceModel = this.motorcycleService.getById(id);

        modelAndView.addObject(motorcycleServiceModel);

        return modelAndView;
    }

    @PostMapping("/delete/{id}")
    public ModelAndView deleteConfirm(@PathVariable String id){


        this.motorcycleService.delete(this.motorcycleService.getById(id));

        return super.redirect("/");
    }

}
