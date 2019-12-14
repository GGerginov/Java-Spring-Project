package project.demo.web.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import project.demo.web.controller.base.BaseController;

@ControllerAdvice
public class GlobalExceptionHandler extends BaseController {

    @ExceptionHandler({Throwable.class})
    public ModelAndView handleException(Throwable e){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error");

        Throwable throwable = e;

        while (throwable.getCause() != null){
            throwable = throwable.getCause();
        }

        modelAndView.addObject("message",throwable.getMessage());

        return modelAndView;
    }
}