package by.alex.bsuir.instagram.controller;

import by.alex.bsuir.instagram.dto.ProfileDTO;
import by.alex.bsuir.instagram.service.profile.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/allUserSearch")
public class SearchController {

    @Autowired
    private ProfileService profileService;

    @RequestMapping(method = RequestMethod.GET)
    public String showAllUsers(Model model) {
        List<ProfileDTO> profiles = profileService.getListOfProfiles();
        model.addAttribute("users", profiles);

        return "user/search";
    }
}
