package by.alex.bsuir.instagram.service.comment.impl;

import by.alex.bsuir.instagram.dao.comment.CommentDao;
import by.alex.bsuir.instagram.dto.CommentDTO;
import by.alex.bsuir.instagram.entity.Comment;
import by.alex.bsuir.instagram.service.comment.CommentService;
import by.alex.bsuir.instagram.util.InstagramConstants;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("commentService")
public class CommentServiceImpl implements CommentService {

    private final Logger LOGGER = Logger.getLogger(getClass());

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private ConversionService conversionService;

    @Override
    @Transactional
    public long saveComment(CommentDTO commentDTO) {
        Comment comment = conversionService.convert(commentDTO, Comment.class);
        long id = commentDao.saveComment(comment);
        LOGGER.info(messageSource.getMessage("service.comment.save", new Object[]{commentDTO},
                InstagramConstants.LOGGER_LOCALE));
        return id;
    }

    @Override
    @Transactional
    public void deleteComment(long id) {
        commentDao.deleteComment(id);
        LOGGER.info(messageSource.getMessage("service.comment.delete", new Object[]{id}, InstagramConstants.LOGGER_LOCALE));
    }

    @Override
    @Transactional
    public void updateComment(CommentDTO commentDTO) {
        Comment comment = conversionService.convert(getComment(commentDTO.getId()), Comment.class);
        commentDao.updateComment(comment);
        LOGGER.info(messageSource.getMessage("service.comment.update", new Object[]{commentDTO}, InstagramConstants.LOGGER_LOCALE));
    }

    @Override
    @Transactional(readOnly = true)
    public CommentDTO getComment(long id) {
        CommentDTO commentDTO = conversionService.convert(commentDao.getComment(id), CommentDTO.class);
        LOGGER.info(messageSource.getMessage("service.comment.getById", new Object[]{id, commentDTO}, InstagramConstants.LOGGER_LOCALE));
        return commentDTO;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommentDTO> getListOfComments() {
        List<Comment> comments = commentDao.getListOfComments();
        List<CommentDTO> commentDTOList = new ArrayList();

        for (Comment comment : comments) {
            commentDTOList.add(conversionService.convert(comment, CommentDTO.class));
        }
        LOGGER.info(messageSource.getMessage("service.comment.getList", new Object[]{commentDTOList}, InstagramConstants.LOGGER_LOCALE));

        return commentDTOList;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommentDTO> getListOfPostsByPostId(long id) {
        List<CommentDTO> commentDTOList = new ArrayList<>();
        List<Comment> comments = commentDao.getListOfPostsByPostId(id);
        for (Comment comment : comments) {
            commentDTOList.add(conversionService.convert(comment, CommentDTO.class));
        }

        LOGGER.info(messageSource.getMessage("service.comment.getListOfPostsByPostId",
                new Object[]{id, commentDTOList}, InstagramConstants.LOGGER_LOCALE));

        return commentDTOList;
    }
}
