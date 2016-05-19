package by.alex.bsuir.instagram.converter.comment;

import by.alex.bsuir.instagram.entity.Comment;
import by.alex.bsuir.instagram.entity.User;
import by.alex.bsuir.instagram.util.InstagramConstants;
import by.alex.bsuir.instagram.dto.CommentDTO;
import by.alex.bsuir.instagram.entity.Post;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CommentDTOToCommentConverter implements Converter<CommentDTO, Comment> {

    private final Logger LOGGER = Logger.getLogger(getClass());

    @Autowired
    private MessageSource messageSource;

    @Override
    public Comment convert(CommentDTO commentDTO) {
        Comment comment = new Comment();
        comment.setCommentId(commentDTO.getId());
        comment.setCommentContent(commentDTO.getCommentContent().trim());

        Post post = new Post();
        post.setPostId(commentDTO.getPost());
        comment.setPost(post);
        User sender = new User();
        sender.setId(commentDTO.getSender());
        comment.setSender(sender);
        User owner = new User();
        owner.setId(commentDTO.getOwner());
        comment.setOwner(owner);

        LOGGER.info(messageSource.getMessage("converter.convert",
                new Object[]{"CommentDTO", "Comment", commentDTO, comment}, InstagramConstants.LOGGER_LOCALE));

        return comment;
    }
}
