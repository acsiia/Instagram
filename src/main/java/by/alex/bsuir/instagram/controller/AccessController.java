package by.alex.bsuir.instagram.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AccessController {

    private final Logger LOGGER = Logger.getLogger(getClass());

    @RequestMapping("/403page")
    public String page403() {
        LOGGER.warn("ACCESS DENIED");

        return "error/403";
    }

    @RequestMapping("/404page")
    public String page404() {

        return "error/404";
    }

    @RequestMapping("/500page")
    public String page500() {
        LOGGER.warn("Internal server error");

        return "error/500";
    }
}
