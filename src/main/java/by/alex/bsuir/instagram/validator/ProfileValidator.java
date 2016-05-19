package by.alex.bsuir.instagram.validator;

import by.alex.bsuir.instagram.util.InstagramConstants;
import by.alex.bsuir.instagram.dto.CountryDTO;
import by.alex.bsuir.instagram.dto.ProfileDTO;
import by.alex.bsuir.instagram.service.country.CountryService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ProfileValidator implements Validator {
    private final Logger LOGGER = Logger.getLogger(getClass());
    private final String FULL_NAME_PATTERN = "^[a-zA-Zа-яА-Я]{0,35}$";

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private CountryService countryService;

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        ProfileDTO profileDTO = (ProfileDTO) target;
        checkFullNameChars(profileDTO, errors);
        checkCityLegalChars(profileDTO.getCity(), errors);
        checkValidCountry(profileDTO);
    }

    private void checkFullNameChars(ProfileDTO profileDTO, Errors errors) {
        Pattern pattern = Pattern.compile(FULL_NAME_PATTERN);

        Matcher firstNameMatcher = pattern.matcher(profileDTO.getFirstName());
        if (!firstNameMatcher.matches()) {
            errors.rejectValue("firstName", "validator.profile.firstnamelegalchars");

            LOGGER.info(messageSource.getMessage("profile.validator.fullname.illegalsymbols",
                    new Object[]{"firstName", profileDTO.getFirstName(), FULL_NAME_PATTERN},
                    InstagramConstants.LOGGER_LOCALE));

        }

        Matcher surnameMatcher = pattern.matcher(profileDTO.getSurname());
        if (!surnameMatcher.matches()) {
            errors.rejectValue("surname", "validator.profile.surnamelegalchars");
            LOGGER.info(messageSource.getMessage("profile.validator.fullname.illegalsymbols",
                    new Object[]{"surname", profileDTO.getSurname(), FULL_NAME_PATTERN},
                    InstagramConstants.LOGGER_LOCALE));
        }

        Matcher secondNameMatcher = pattern.matcher(profileDTO.getSecondName());
        if (!secondNameMatcher.matches()) {
            errors.rejectValue("secondName", "validator.profile.secondnamelegalchars");

            LOGGER.info(messageSource.getMessage("profile.validator.fullname.illegalsymbols",
                    new Object[]{"secondName", profileDTO.getSecondName(), FULL_NAME_PATTERN},
                    InstagramConstants.LOGGER_LOCALE));
        }
    }

    private void checkCityLegalChars(String city, Errors errors) {
        Pattern pattern = Pattern.compile(FULL_NAME_PATTERN);

        Matcher cityMatcher = pattern.matcher(city);
        if (!cityMatcher.matches()) {
            errors.rejectValue("city", "validator.profile.cityillegaksymbols");

            LOGGER.info(messageSource.getMessage("profile.validator.fullname.illegalsymbols",
                    new Object[]{"city", city, FULL_NAME_PATTERN},
                    InstagramConstants.LOGGER_LOCALE));
        }
    }

    private void checkValidCountry(ProfileDTO profileDTO) {
        long countryId = Long.valueOf(profileDTO.getCountryID());
        CountryDTO country = countryService.getCountry(countryId);
        profileDTO.setCountryDTO(country);
    }
}
