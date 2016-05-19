package by.alex.bsuir.instagram.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(Exception.class)
    public ModelAndView handleError(HttpServletRequest request, Exception exception) {
        ModelAndView model = new ModelAndView();
        model.addObject("exception", exception);
        model.addObject("url", request.getRequestURL());
        model.setViewName("error/error");

        return model;
    }
}
