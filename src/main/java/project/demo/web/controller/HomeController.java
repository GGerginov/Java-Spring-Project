package project.demo.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import project.demo.web.controller.base.BaseController;

@Controller
public class HomeController extends BaseController {


    @GetMapping("/")
    public ModelAndView home(){

        return super.view("index");
    }

    @GetMapping("/contacts")
    public ModelAndView contacts(){

        return super.view("contacts");
    }
}
