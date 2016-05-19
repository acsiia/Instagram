package by.alex.bsuir.instagram.service.user.impl;

import by.alex.bsuir.instagram.dao.user.UserDao;
import by.alex.bsuir.instagram.dto.PostDTO;
import by.alex.bsuir.instagram.dto.UserDTO;
import by.alex.bsuir.instagram.entity.User;
import by.alex.bsuir.instagram.service.user.UserService;
import by.alex.bsuir.instagram.util.InstagramConstants;
import by.alex.bsuir.instagram.dto.CommentDTO;
import by.alex.bsuir.instagram.util.enums.UserRoleEnum;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    private final Logger LOGGER = Logger.getLogger(getClass());

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private UserDao userDao;

    @Autowired
    private ConversionService conversionService;

    @Override
    @Transactional
    public long saveUser(UserDTO userDTO) {
        User user = conversionService.convert(userDTO, User.class);
        long id = userDao.saveUser(user);
        LOGGER.info(messageSource.getMessage("service.user.save", new Object[]{userDTO},
                InstagramConstants.LOGGER_LOCALE));
        return id;
    }

    @Override
    @Transactional
    public void deleteUser(long id) {
        userDao.deleteUserById(id);
        LOGGER.info(messageSource.getMessage("service.user.delete", new Object[]{id},
                InstagramConstants.LOGGER_LOCALE));
    }

    @Override
    @Transactional
    public void updateUser(UserDTO userDTO) {
        User user = conversionService.convert(userDTO, User.class);
        userDao.updateUser(user);
        LOGGER.info(messageSource.getMessage("service.user.update", new Object[]{userDTO},
                InstagramConstants.LOGGER_LOCALE));
    }

    @Override
    @Transactional(readOnly = true)
    public UserDTO getUserByLogin(String login) {
        UserDTO userDTO = conversionService.convert(userDao.getUserByName(login), UserDTO.class);
        LOGGER.info(messageSource.getMessage("service.user.getUserByLogin",
                new Object[]{login, userDTO}, InstagramConstants.LOGGER_LOCALE));

        return userDTO;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDTO getUserByEmail(String email) {
        UserDTO userDTO = conversionService.convert(userDao.getUserByEmail(email), UserDTO.class);
        LOGGER.info(messageSource.getMessage("service.user.getUserByEmail",
                new Object[]{email, userDTO}, InstagramConstants.LOGGER_LOCALE));

        return userDTO;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDTO getUserById(long id) {
        UserDTO userDTO = conversionService.convert(userDao.getUser(id), UserDTO.class);
        LOGGER.info(messageSource.getMessage("service.user.getUserById", new Object[]{id, userDTO},
                InstagramConstants.LOGGER_LOCALE));

        return userDTO;
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDTO> getListOfUsers() {
        List<User> users = userDao.getListOfUsers();
        List<UserDTO> userDTOList = new ArrayList();
        for (User user : users) {
            if (user.getRole().equals(UserRoleEnum.USER.getRole())) {
                userDTOList.add(conversionService.convert(user, UserDTO.class));
            }
        }
        LOGGER.info(messageSource.getMessage("service.user.getList", new Object[]{userDTOList},
                InstagramConstants.LOGGER_LOCALE));

        return userDTOList;
    }

    @Override
    @Transactional(readOnly = true)
    public void setPostSendersUsernames(List<PostDTO> posts) {
        for (PostDTO post : posts) {
            post.setSenderName(getUserById(post.getSender()).getLogin());
        }

        LOGGER.info(messageSource.getMessage("service.user.setPostSendersUsernames", null,
                InstagramConstants.LOGGER_LOCALE));
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDTO> findByPattern(String pattern) {
        List<User> users = userDao.findByPattern(pattern);
        List<UserDTO> userDTOList = new ArrayList<>();
        for (User user : users) {
            userDTOList.add(conversionService.convert(user, UserDTO.class));
        }

        return userDTOList;
    }

    @Override
    @Transactional
    public UserDTO changeEnableField(long id) {
        UserDTO userDTO = getUserById(id);
        if (userDTO.isEnable()) {
            userDTO.setEnable(false);
        } else {
            userDTO.setEnable(true);
        }
        updateUser(userDTO);

        return userDTO;
    }

    @Override
    @Transactional(readOnly = true)
    public void setCommentSendersNames(List<CommentDTO> comments) {
        for (CommentDTO comment : comments) {
            comment.setSenderName(getUserById(comment.getSender()).getLogin());
        }

        LOGGER.info(messageSource.getMessage("service.user.setCommentSendersNames",
                new Object[]{}, InstagramConstants.LOGGER_LOCALE));

    }
}
