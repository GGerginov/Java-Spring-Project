package project.demo.web.controller;

import com.cloudinary.Cloudinary;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import project.demo.service.CarService;
import project.demo.service.CloudinaryService;
import project.demo.service.models.CarServiceModel;
import project.demo.web.controller.base.BaseController;
import project.demo.web.models.SaleCreateBindingModel;
import project.demo.web.models.SearchCarBindingModel;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/cars")

public class CarController extends BaseController {

    private ModelMapper modelMapper;

    private CarService carService;

    private CloudinaryService cloudinaryService;

    @Autowired
    public CarController(ModelMapper modelMapper, CarService carService, CloudinaryService cloudinaryService) {
        this.modelMapper = modelMapper;
        this.carService = carService;
        this.cloudinaryService = cloudinaryService;
    }

    @GetMapping("/create")
    public ModelAndView createCar(){

        return super.view("submit");
    }

    @PostMapping("/create")
    public ModelAndView crateSale(@ModelAttribute SaleCreateBindingModel model){


        CarServiceModel carServiceModel = this.modelMapper.map(model,CarServiceModel.class);

        try {
            carServiceModel.setImageUrl(this.cloudinaryService.uploadImage(model.getImage()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.carService.publish(carServiceModel);

        return redirect("/");
    }

    @GetMapping("/search")
    public ModelAndView listAllConfirm(){

        return super.view("vehiculs");
    }

    @PostMapping("/search")
    public ModelAndView searchCarsConfirm(@ModelAttribute SearchCarBindingModel model){

        List<CarServiceModel> allBy = this.carService.getAllByManufacturerAndModel(model.getManufacturer(),model.getModel());

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("vehiculs");
        modelAndView.addObject(allBy);
        return modelAndView;
    }

    @GetMapping("/show/{id}")
    public ModelAndView show(@PathVariable String id){

        ModelAndView modelAndView = new ModelAndView();

        CarServiceModel car = this.carService.getById(id);
        modelAndView.addObject(car);
        modelAndView.setViewName("cars/car-show");

        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable String id){

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("cars/car-edit");

        CarServiceModel carServiceModel = this.carService.getById(id);

        modelAndView.addObject(carServiceModel);

        return modelAndView;
    }

    @PostMapping("/edit/{id}")
    public ModelAndView modelAndView(@PathVariable String id,@ModelAttribute CarServiceModel model){


        this.carService.delete(this.carService.getById(id));

        this.carService.publish(model);

        return super.redirect("/");
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable String id){

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("cars/car-delete");

        CarServiceModel carServiceModel = this.carService.getById(id);

        modelAndView.addObject(carServiceModel);

        return modelAndView;
    }

    @PostMapping("/delete/{id}")

    public ModelAndView deleteConfirm(@PathVariable String id){


        this.carService.delete(this.carService.getById(id));

        return super.redirect("/");
    }







}
