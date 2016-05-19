package by.alex.bsuir.instagram.converter.profile;

import by.alex.bsuir.instagram.dto.ProfileDTO;
import by.alex.bsuir.instagram.entity.Profile;
import by.alex.bsuir.instagram.util.InstagramConstants;
import by.alex.bsuir.instagram.entity.Country;
import by.alex.bsuir.instagram.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ProfileDTOToProfileConverter implements Converter<ProfileDTO, Profile> {
    private final Logger LOGGER = Logger.getLogger(getClass());

    @Autowired
    private MessageSource messageSource;

    @Override
    public Profile convert(ProfileDTO profileDTO) {
        Profile profile = new Profile();
        profile.setId(profileDTO.getId());
        trimNecessaryStrings(profile, profileDTO);
        profile.setSex(profileDTO.getSex());

        convertPostImage(profileDTO, profile);
        convertBirthdayDate(profileDTO, profile);
        convertCountry(profileDTO, profile);

        User user = new User();
        user.setId(profileDTO.getUser());
        profile.setUser(user);

        LOGGER.info(messageSource.getMessage("converter.convert",
                new Object[]{"ProfileDTO", "Profile", profileDTO, profile}, InstagramConstants.LOGGER_LOCALE));
        return profile;
    }

    private void trimNecessaryStrings(Profile profile, ProfileDTO profileDTO) {
        if (profileDTO.getFirstName() != null) {
            profile.setFirstName(profileDTO.getFirstName().trim());
        } else {
            profile.setFirstName(profileDTO.getFirstName());
        }
        if (profileDTO.getSecondName() != null) {
            profile.setSurname(profileDTO.getSurname().trim());
        } else {
            profile.setSurname(profileDTO.getSurname());
        }
        if (profileDTO.getSecondName() != null) {
            profile.setSecondName(profileDTO.getSecondName().trim());
        } else {
            profile.setSecondName(profileDTO.getSecondName());
        }
        if (profileDTO.getCity() != null) {
            profile.setCity(profileDTO.getCity().trim());
        } else {
            profile.setCity(profileDTO.getCity());
        }
    }

    private void convertPostImage(ProfileDTO profileDTO, Profile profile) {
        try {
            if (profileDTO.getPicture() != null && profileDTO.getPicture().getBytes().length > 0) {
                profileDTO.setAvatar(profileDTO.getPicture().getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (profileDTO.getAvatar() == null || profileDTO.getAvatar().length <= 0) {
            profile.setAvatar(null);
        } else {
            profile.setAvatar(profileDTO.getAvatar());
        }
    }

    private void convertBirthdayDate(ProfileDTO profileDTO, Profile profile) {
        if (StringUtils.isBlank(profileDTO.getBirthday())) {
            profile.setBirthday(null);
        } else {
            SimpleDateFormat dateFormat = new SimpleDateFormat(InstagramConstants.BIRTHDAY_DATE_FORMAT);
            Date date = null;
            try {
                date = dateFormat.parse(profileDTO.getBirthday());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            profile.setBirthday(new java.sql.Date(date.getTime()));
        }
    }

    private void convertCountry(ProfileDTO profileDTO, Profile profile) {
        if (profileDTO.getCountryDTO() == null) {
            profile.setCountry(null);
        } else {
            Country country = new Country();
            country.setCountryId(profileDTO.getCountryDTO().getCountryId());
            profile.setCountry(country);
        }
    }

}
