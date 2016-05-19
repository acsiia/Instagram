package by.alex.bsuir.instagram.dto;


import by.alex.bsuir.instagram.jsonview.Views;
import by.alex.bsuir.instagram.util.enums.UserRoleEnum;
import com.fasterxml.jackson.annotation.JsonView;

public class UserDTO {
    @JsonView(Views.Search.class)
    private Long userId;
    @JsonView(Views.Search.class)
    private String login;
    @JsonView(Views.Search.class)
    private String fullName;
    private String password;
    private String repeatedPassword;
    private String email;
    private UserRoleEnum role;
    private boolean enable;


    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getRepeatedPassword() {
        return repeatedPassword;
    }

    public void setRepeatedPassword(String repeatedPassword) {
        this.repeatedPassword = repeatedPassword;
    }

    public UserRoleEnum getRole() {
        return role;
    }

    public void setRole(UserRoleEnum role) {
        this.role = role;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
}
