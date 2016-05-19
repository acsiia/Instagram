package by.alex.bsuir.instagram.service.user;

import by.alex.bsuir.instagram.dto.PostDTO;
import by.alex.bsuir.instagram.dto.UserDTO;
import by.alex.bsuir.instagram.dto.CommentDTO;
import org.hibernate.exception.ConstraintViolationException;

import java.util.List;

public interface UserService {
    long saveUser(UserDTO userDTO) throws ConstraintViolationException;
    void deleteUser(long id);
    void updateUser(UserDTO userDTO);
    UserDTO getUserByLogin(String login);
    UserDTO getUserByEmail(String email);
    UserDTO getUserById(long id);
    List<UserDTO> getListOfUsers();
    void setCommentSendersNames(List<CommentDTO> comments);
    void setPostSendersUsernames(List<PostDTO> posts);
    List<UserDTO> findByPattern(String pattern);
    UserDTO changeEnableField(long id);
}
