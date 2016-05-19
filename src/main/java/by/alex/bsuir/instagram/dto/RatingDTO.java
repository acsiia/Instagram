package by.alex.bsuir.instagram.dto;

import by.alex.bsuir.instagram.util.enums.RatingTypeEnum;

public class RatingDTO {
    private Long ratingId;
    private Long post;
    private Long sender;
    private RatingTypeEnum type;

    public Long getRatingId() {
        return ratingId;
    }

    public void setRatingId(Long ratingId) {
        this.ratingId = ratingId;
    }

    public Long getPost() {
        return post;
    }

    public void setPost(Long post) {
        this.post = post;
    }

    public Long getSender() {
        return sender;
    }

    public void setSender(Long sender) {
        this.sender = sender;
    }

    public RatingTypeEnum getType() {
        return type;
    }

    public void setType(RatingTypeEnum type) {
        this.type = type;
    }
}
