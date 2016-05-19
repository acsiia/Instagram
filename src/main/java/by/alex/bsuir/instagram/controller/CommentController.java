package by.alex.bsuir.instagram.controller;

import by.alex.bsuir.instagram.dto.CommentDTO;
import by.alex.bsuir.instagram.dto.UserDTO;
import by.alex.bsuir.instagram.jsonview.Views;
import by.alex.bsuir.instagram.service.AuthorizationService;
import by.alex.bsuir.instagram.service.user.UserService;
import by.alex.bsuir.instagram.service.comment.CommentService;
import by.alex.bsuir.instagram.validator.MessageContentValidator;
import com.fasterxml.jackson.annotation.JsonView;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/comment")
public class CommentController {

    @Autowired
    private MessageContentValidator messageContentValidator;

    @Autowired
    private CommentService commentService;

    @Autowired
    private AuthorizationService authorizationService;

    @Autowired
    private UserService userService;

    @JsonView(Views.Comment.class)
    @RequestMapping(value = "/addComment", method = RequestMethod.POST)
    public CommentDTO addComment(@RequestBody CommentDTO comment) {
        comment.setCommentContent(messageContentValidator.encodeMessage(comment.getCommentContent()));
        comment.setCommentContent(messageContentValidator.findLink(comment.getCommentContent()));
        UserDTO authUser = authorizationService.getAuthUser();
        comment.setSender(authUser.getUserId());
        if (StringUtils.isBlank(comment.getCommentContent())) {
            return null;
        }
        comment = commentService.getComment(commentService.saveComment(comment));
        comment.setSenderName(authUser.getLogin());

        return comment;
    }

    @JsonView(Views.Comment.class)
    @RequestMapping(value = "/getCommentOfPost", method = RequestMethod.GET)
    public List<CommentDTO> getComments(@RequestParam Long id) {
        List<CommentDTO> comments = commentService.getListOfPostsByPostId(id);
        userService.setCommentSendersNames(comments);

        return comments;
    }

    @JsonView(Views.Comment.class)
    @RequestMapping(value = "/deleteComment", method = RequestMethod.GET)
    public void removePost(@RequestParam Long id) {
        UserDTO authUser = authorizationService.getAuthUser();
        CommentDTO comment = commentService.getComment(id);

        if (authUser.getUserId().equals(comment.getSender()) || authUser.getUserId().equals(comment.getOwner())) {
            commentService.deleteComment(id);
        }
    }
}
