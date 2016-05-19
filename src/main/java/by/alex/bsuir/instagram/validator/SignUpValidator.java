package by.alex.bsuir.instagram.validator;

import by.alex.bsuir.instagram.dto.UserDTO;
import by.alex.bsuir.instagram.service.user.UserService;
import by.alex.bsuir.instagram.util.InstagramConstants;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Component
public class SignUpValidator implements Validator {

    private final Logger LOGGER = Logger.getLogger(getClass());

    @Autowired
    private MessageSource messageSource;

    private Pattern pattern;

    private final String LOGIN_PATTERN = "^[a-zA-Z0-9_.]{1,20}$";

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }

    @Override
    public void validate(Object user, Errors errors) {
        UserDTO userDTO = (UserDTO) user;

        if (checkValidChars(userDTO.getLogin(), errors)) {
            LOGGER.warn(messageSource.getMessage("signup.validator.invalidchars",
                    new Object[]{LOGIN_PATTERN}, InstagramConstants.LOGGER_LOCALE));
            return;
        } else if (checkEmptyValue(userDTO, errors)) {
            LOGGER.warn(messageSource.getMessage("signup.validator.isEmpty",
                    null, InstagramConstants.LOGGER_LOCALE));
            return;
        } else {
            checkLoginRepeat(userDTO.getLogin(), errors);
            checkEmailRepeat(userDTO.getEmail(), errors);
            checkEqualPassword(userDTO.getPassword(), userDTO.getRepeatedPassword(), errors);
        }

    }

    private boolean checkValidChars(String login, Errors errors) {
        boolean error = false;
        pattern = Pattern.compile(LOGIN_PATTERN);
        Matcher matcher = pattern.matcher(login);

        if (!matcher.matches()) {
            errors.rejectValue("login", "validator.registration.loginillegalchars");
            error = true;
        }

        return error;
    }

    private void checkEqualPassword(String password, String repeatedPassword, Errors errors) {
        if (!password.equals(repeatedPassword)) {
            errors.rejectValue("password", "validator.registration.passwordsnotequal");
            errors.rejectValue("repeatedPassword", "validator.registration.passwordsnotequal");
        }
    }

    private void checkLoginRepeat(String login, Errors errors) {
        if (userService.getUserByLogin(login) != null) {
            errors.rejectValue("login", "validator.registration.loginalreadyexists");

            LOGGER.warn(messageSource.getMessage("signup.validator.loginExists",
                    null, InstagramConstants.LOGGER_LOCALE));
        }

        LOGGER.info(messageSource.getMessage("signup.validator.loginNotExists",
                null, InstagramConstants.LOGGER_LOCALE));
    }

    private void checkEmailRepeat(String email, Errors errors) {
        if (userService.getUserByEmail(email) != null) {
            errors.rejectValue("email", "validator.registration.emailalreadyexits");

            LOGGER.warn(messageSource.getMessage("signup.validator.emailExists",
                    null, InstagramConstants.LOGGER_LOCALE));
        }

        LOGGER.info(messageSource.getMessage("signup.validator.emailNotExists",
                null, InstagramConstants.LOGGER_LOCALE));
    }

    private boolean checkEmptyValue(UserDTO userDTO, Errors errors) {
        boolean error = false;
        if (!StringUtils.hasText(userDTO.getLogin())) {
            errors.rejectValue("login", "validator.registration.cannotbeempty");
            error = true;
        }
        if (!StringUtils.hasText(userDTO.getPassword())) {
            errors.rejectValue("password", "validator.registration.cannotbeempty");
            error = true;
        }
        if (!StringUtils.hasText(userDTO.getRepeatedPassword())) {
            errors.rejectValue("repeatedPassword", "validator.registration.cannotbeempty");
            error = true;
        }
        if (!StringUtils.hasText(userDTO.getEmail())) {
            errors.rejectValue("email", "validator.registration.cannotbeempty");
            error = true;
        }

        return error;
    }

    //TODO block admin deleted
}
