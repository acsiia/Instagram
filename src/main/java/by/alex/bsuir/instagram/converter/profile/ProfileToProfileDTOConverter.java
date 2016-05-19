package by.alex.bsuir.instagram.converter.profile;

import by.alex.bsuir.instagram.dto.ProfileDTO;
import by.alex.bsuir.instagram.entity.Profile;
import by.alex.bsuir.instagram.util.InstagramConstants;
import by.alex.bsuir.instagram.dto.CountryDTO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

@Component
public class ProfileToProfileDTOConverter implements Converter<Profile, ProfileDTO> {
    private final Logger LOGGER = Logger.getLogger(getClass());

    @Autowired
    private MessageSource messageSource;

    @Override
    public ProfileDTO convert(Profile profile) {
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setId(profile.getId());
        profileDTO.setFirstName(profile.getFirstName());
        profileDTO.setSurname(profile.getSurname());
        profileDTO.setSecondName(profile.getSecondName());

        convertCountry(profileDTO, profile);

        profileDTO.setCity(profile.getCity());
        profileDTO.setSex(profile.getSex());
        profileDTO.setAvatar(profile.getAvatar());
        convertBirthdayDate(profileDTO, profile);

        profileDTO.setUser(profile.getUser().getId());

        LOGGER.info(messageSource.getMessage("converter.convert",
                new Object[]{"Profile", "ProfileDTO", profile, profileDTO}, InstagramConstants.LOGGER_LOCALE));

        return profileDTO;
    }

    private void convertBirthdayDate(ProfileDTO profileDTO, Profile profile) {
        if (profile.getBirthday() == null) {
            profileDTO.setBirthday(null);
        } else {
            SimpleDateFormat dateFormat = new SimpleDateFormat(InstagramConstants.BIRTHDAY_DATE_FORMAT);
            String date = dateFormat.format(profile.getBirthday());
            profileDTO.setBirthday(date);
            SimpleDateFormat viewDateFormat = new SimpleDateFormat(InstagramConstants.VIEW_BIRTHDAY_DATE_FORMAT);
            String viewDate = viewDateFormat.format(profile.getBirthday());
            profileDTO.setBirthday(viewDate);
        }
    }

    private void convertCountry(ProfileDTO profileDTO, Profile profile) {
        if (profile.getCountry() == null) {
            profileDTO.setCountry(null);
        } else {
            CountryDTO countryDTO = new CountryDTO();
            countryDTO.setCountryRu(profile.getCountry().getCountryRu());
            countryDTO.setCountryEn(profile.getCountry().getCountryEn());
            countryDTO.setCountryId(profile.getCountry().getCountryId());
            profileDTO.setCountryDTO(countryDTO);
        }
    }
}
