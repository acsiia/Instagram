package by.alex.bsuir.instagram.util.enums;

public enum  RatingTypeEnum {
    LIKE("like"),
    DISLIKE("dislike");

    private String type;

    RatingTypeEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
