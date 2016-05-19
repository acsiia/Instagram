package by.alex.bsuir.instagram.converter.comment;

import by.alex.bsuir.instagram.dto.CommentDTO;
import by.alex.bsuir.instagram.entity.Comment;
import by.alex.bsuir.instagram.util.InstagramConstants;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CommentToCommentDTOConverter implements Converter<Comment, CommentDTO> {

    private final Logger LOGGER = Logger.getLogger(getClass());

    @Autowired
    private MessageSource messageSource;

    @Override
    public CommentDTO convert(Comment comment) {
        if (comment == null) {
            return null;
        }
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(comment.getCommentId());
        commentDTO.setCommentContent(comment.getCommentContent());
        commentDTO.setPost(comment.getPost().getPostId());
        commentDTO.setSender(comment.getSender().getId());
        commentDTO.setOwner(comment.getOwner().getId());

        LOGGER.info(messageSource.getMessage("converter.convert",
                new Object[]{"Comment", "CommentDTO", comment, commentDTO}, InstagramConstants.LOGGER_LOCALE));

        return commentDTO;
    }
}
