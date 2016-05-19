package by.alex.bsuir.instagram.controller;

import by.alex.bsuir.instagram.dto.ProfileDTO;
import by.alex.bsuir.instagram.dto.UserDTO;
import by.alex.bsuir.instagram.validator.ProfileValidator;
import by.alex.bsuir.instagram.service.AuthorizationService;
import by.alex.bsuir.instagram.service.country.CountryService;
import by.alex.bsuir.instagram.service.profile.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.io.IOException;
import java.util.Locale;
import java.util.Map;

@Controller
@SessionAttributes({"authorizedUser"})
@RequestMapping(value = "/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @Autowired
    private ProfileValidator profileValidator;

    @Autowired
    private AuthorizationService authorizationService;

    @Autowired
    private CountryService countryService;

    @RequestMapping(value = "/user={id}", method = RequestMethod.GET)
    public String showProfile(@PathVariable("id") long currentUserId, Model model) {
        UserDTO authUser = authorizationService.getAuthUser();
        ProfileDTO profileDTO = profileService.getProfileByUserId(currentUserId);
        if (profileDTO == null) {

            return "redirect:/profile/user=" + authUser.getUserId();
        }
        if (currentUserId == authUser.getUserId()) {
            model.addAttribute("isEditable", true);
        } else {
            model.addAttribute("isEditable", false);
        }
        ProfileDTO editedProfile = new ProfileDTO();
        editedProfile.setId(profileDTO.getId());
        editedProfile.setUser(profileDTO.getUser());
        editedProfile.setAvatar(profileDTO.getAvatar());
        setUserCountry(profileDTO, editedProfile);
        model.addAttribute("profile", profileDTO);
        model.addAttribute("editedProfile", editedProfile);
        Map<Long, String> countryMap = countryService.getCountriesByLocale();
        model.addAttribute("countryList", countryMap);

        return "profile/profile";
    }

    @RequestMapping(value = "/updateProfile", method = {RequestMethod.POST, RequestMethod.GET})
    public String updateProfile(@ModelAttribute("editedProfile") ProfileDTO profile, BindingResult result, Model model)
            throws IOException {
        if (profile.getId() == null) {
            UserDTO authUser = authorizationService.getAuthUser();
            ProfileDTO authUserProfile = profileService.getProfileByUserId(authUser.getUserId());

            return "redirect:/profile/user=" + authUserProfile.getUser();
        }
        profileValidator.validate(profile, result);
        if (result.hasErrors()) {
            model.addAttribute("isNotValid", true);
            model.addAttribute("isEditable", true);
            ProfileDTO profileDTO = profileService.getProfileByUserId(profile.getUser());
            model.addAttribute("profile", profileDTO);
            model.addAttribute("editedProfile", profile);

            return "profile/profile";
        } else {
            model.addAttribute("isNotValid", false);
        }
        profileService.updateProfile(profile);
        return "redirect:/profile/user=" + profile.getUser();
    }

    private void setUserCountry(ProfileDTO profileDTO, ProfileDTO editedProfile) {
        if (profileDTO.getCountryDTO() == null) {
            profileDTO.setCountry("");
            editedProfile.setCountryID("0");
        } else {
            if (LocaleContextHolder.getLocale().getLanguage().equals(new Locale("ru").getLanguage())) {
                profileDTO.setCountry(profileDTO.getCountryDTO().getCountryRu());
                editedProfile.setCountryID(profileDTO.getCountryDTO().getCountryId().toString());
            } else if (LocaleContextHolder.getLocale().getLanguage().equals(new Locale("en").getLanguage())) {
                profileDTO.setCountry(profileDTO.getCountryDTO().getCountryEn());
                editedProfile.setCountryID(profileDTO.getCountryDTO().getCountryId().toString());
            }
        }
    }
}
