package by.alex.bsuir.instagram.util.enums;

public enum UserRoleEnum {
    ADMIN("ADMIN"),
    USER("USER");

    private String role;

    UserRoleEnum(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
