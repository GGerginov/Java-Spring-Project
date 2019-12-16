package project.demo.web.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import project.demo.service.CloudinaryService;
import project.demo.service.UserService;
import project.demo.service.models.UserServiceModel;
import project.demo.web.controller.base.BaseController;
import project.demo.web.models.users.UserLoginBindingModel;
import project.demo.web.models.users.UserRegisterBindingModel;

import java.io.IOException;


@Controller
@RequestMapping("/users")
public class UserController extends BaseController {

    private final ModelMapper modelMapper;

    private final UserService userService;

    private final CloudinaryService cloudinaryService;

    @Autowired
    public UserController(ModelMapper modelMapper, UserService userService, CloudinaryService cloudinaryService) {
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.cloudinaryService = cloudinaryService;
    }

    @GetMapping("/register")
    public ModelAndView register(){

        return super.view("register");
    }

    @PostMapping("/register")
    public ModelAndView registerConfirm(@ModelAttribute UserRegisterBindingModel model){

        if (model.getPassword().equals(model.getConfirmPassword())){

            UserServiceModel userServiceModel = this.modelMapper.map(model,UserServiceModel.class);

            try {
                userServiceModel.setProfilePictureUrl(this.cloudinaryService.uploadImage(model.getImage()));
            } catch (IOException e) {
                e.printStackTrace();
            }

            this.userService.register(userServiceModel);

            return super.view("login");
        }

        return redirect("/register");
    }

    @GetMapping("/login")
    public ModelAndView login(){

        return super.view("login");
    }

    @PostMapping("/login")
    public ModelAndView loginConfirm(@ModelAttribute UserLoginBindingModel model){


        if (this.userService.getByUserNameAndPassword(model.getUsername(),model.getPassword()) == null) {

            super.redirect("register");
        }

        return redirect("/");
    }
}
