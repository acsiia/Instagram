package by.alex.bsuir.instagram.controller;

import by.alex.bsuir.instagram.dto.UserDTO;
import by.alex.bsuir.instagram.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public String adminPage(Map<String, Object> model) {
        List<UserDTO> userDTOList = userService.getListOfUsers();
        model.put("users", userDTOList);

        return "user/admin";
    }

    @RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
    public @ResponseBody void deleteUser(@RequestParam long id) {
        userService.deleteUser(id);
    }

    @RequestMapping(value = "/banUser", method = RequestMethod.GET)
    public @ResponseBody boolean banUser(@RequestParam long id) {
        return userService.changeEnableField(id).isEnable();
    }
}
