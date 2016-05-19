package by.alex.bsuir.instagram.dto;

import by.alex.bsuir.instagram.jsonview.Views;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.web.multipart.MultipartFile;


public class PostDTO {

    @JsonView(Views.Rating.class)
    private Long id;
    private String postContent;
    private MultipartFile picture;
    private byte[] imageByte;

    @JsonView(Views.Rating.class)
    private Long like;

    @JsonView(Views.Rating.class)
    private Long dislike;
    private String senderName;
    private String dateDispatch;
    private byte[] senderAvatar;
    private Long sender;
    private Long owner;


    public MultipartFile getPicture() {
        return picture;
    }

    public void setPicture(MultipartFile picture) {
        this.picture = picture;
    }

    public byte[] getImageByte() {
        return imageByte;
    }

    public void setImageByte(byte[] imageByte) {
        this.imageByte = imageByte;
    }

    public void setLike(Long like) {
        this.like = like;
    }

    public Long getLike() {
        return like;
    }

    public Long getDislike() {
        return dislike;
    }

    public void setDislike(Long dislike) {
        this.dislike = dislike;
    }

    public Long getSender() {
        return sender;
    }

    public void setSender(Long sender) {
        this.sender = sender;
    }

    public Long getOwner() {
        return owner;
    }

    public void setOwner(Long owner) {
        this.owner = owner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public String getDateDispatch() {
        return dateDispatch;
    }

    public void setDateDispatch(String dateDispatch) {
        this.dateDispatch = dateDispatch;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public byte[] getSenderAvatar() {
        return senderAvatar;
    }

    public void setSenderAvatar(byte[] senderAvatar) {
        this.senderAvatar = senderAvatar;
    }
}
