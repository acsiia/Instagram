package by.alex.bsuir.instagram.dto;

import by.alex.bsuir.instagram.jsonview.Views;
import com.fasterxml.jackson.annotation.JsonView;

public class CommentDTO {
    @JsonView(Views.Comment.class)
    private Long id;

    @JsonView(Views.Comment.class)
    private String commentContent;

    @JsonView(Views.Comment.class)
    private Long post;

    @JsonView(Views.Comment.class)
    private String senderName;

    @JsonView(Views.Comment.class)
    private Long sender;

    @JsonView(Views.Comment.class)
    private Long owner;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public Long getOwner() {
        return owner;
    }

    public void setOwner(Long owner) {
        this.owner = owner;
    }
}
