package project.demo.web.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import project.demo.service.UserService;
import project.demo.service.models.UserServiceModel;
import project.demo.web.controller.base.BaseController;
import project.demo.web.models.users.UserLoginBindingModel;
import project.demo.web.models.users.UserRegisterBindingModel;


@Controller
@RequestMapping("/users")
public class UserController extends BaseController {

    private final ModelMapper modelMapper;

    private final UserService userService;

    @Autowired
    public UserController(ModelMapper modelMapper, UserService userService) {
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @GetMapping("/register")
    public ModelAndView register(){

        return super.view("users/register");
    }

    @PostMapping("/register")
    public ModelAndView registerConfirm(@ModelAttribute UserRegisterBindingModel model){

        if (model.getPassword().equals(model.getConfirmPassword())){

            this.userService.register(this.modelMapper.map(model, UserServiceModel.class));

            return super.view("users/login");
        }

        return redirect("users/register");
    }

    @GetMapping("/login")
    public ModelAndView login(){

        return super.view("users/login");
    }

    @PostMapping("/login")
    public ModelAndView loginConfirm(@ModelAttribute UserLoginBindingModel model){


        if (this.userService.getByUserNameAndPassword(model.getUsername(),model.getPassword()) == null) {

            super.redirect("users/register");
        }

        return redirect("/");
    }
}
