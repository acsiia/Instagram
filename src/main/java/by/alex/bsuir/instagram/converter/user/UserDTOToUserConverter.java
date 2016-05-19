package by.alex.bsuir.instagram.converter.user;

import by.alex.bsuir.instagram.dto.UserDTO;
import by.alex.bsuir.instagram.util.InstagramConstants;
import by.alex.bsuir.instagram.entity.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserDTOToUserConverter implements Converter<UserDTO, User> {

    private final Logger LOGGER = Logger.getLogger(getClass());

    @Autowired
    private MessageSource messageSource;

    @Override
    public User convert(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getUserId());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setLogin(userDTO.getLogin());
        user.setRole(userDTO.getRole().getRole());
        user.setEnable(userDTO.isEnable());

        LOGGER.info(messageSource.getMessage("converter.convert",
                new Object[]{"UserDTO", "User", userDTO, user}, InstagramConstants.LOGGER_LOCALE));

        return user;
    }
}
