package by.alex.bsuir.instagram.dao.comment.impl;

import by.alex.bsuir.instagram.dao.comment.CommentDao;
import by.alex.bsuir.instagram.entity.Comment;
import by.alex.bsuir.instagram.util.InstagramConstants;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("commentDao")
public class CommentDaoImpl implements CommentDao {

    private final Logger LOGGER = Logger.getLogger(getClass());

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public long saveComment(Comment comment) {
        sessionFactory.getCurrentSession().save(comment);
        LOGGER.info(messageSource.getMessage("dao.comment.save", new Object[]{comment},
                InstagramConstants.LOGGER_LOCALE));
        return comment.getCommentId();
    }

    @Override
    public void deleteComment(long id) {
        Comment comment = new Comment();
        comment.setCommentId(id);
        Comment mergedComment = (Comment) sessionFactory.getCurrentSession().merge(comment);
        sessionFactory.getCurrentSession().delete(mergedComment);

        LOGGER.info(messageSource.getMessage("dao.comment.delete", new Object[]{mergedComment},
                InstagramConstants.LOGGER_LOCALE));
    }

    @Override
    public void updateComment(Comment comment) {
        Comment mergedComment = (Comment) sessionFactory.getCurrentSession().merge(comment);
        sessionFactory.getCurrentSession().update(mergedComment);
        LOGGER.info(messageSource.getMessage("dao.comment.update", new Object[]{comment},
                InstagramConstants.LOGGER_LOCALE));
    }

    @Override
    public Comment getComment(long id) {
        Comment comment = sessionFactory.getCurrentSession().get(Comment.class, id);
        LOGGER.info(messageSource.getMessage("dao.comment.getById", new Object[]{id},
                InstagramConstants.LOGGER_LOCALE));

        return comment;
    }

    @Override
    public List<Comment> getListOfComments() {
        List<Comment> comments = sessionFactory.getCurrentSession().createCriteria(Comment.class).list();
        LOGGER.info(messageSource.getMessage("dao.comment.getList", new Object[] {comments},
                InstagramConstants.LOGGER_LOCALE));

        return comments;
    }

    @Override
    public List<Comment> getListOfPostsByPostId(long id) {
        String posthql = "FROM  by.alex.bsuir.instagram.entity.Comment C WHERE C.post.postId = :id";
        Query query = sessionFactory.getCurrentSession().createQuery(posthql);
        query.setParameter("id", id);
        List<Comment> comments = query.list();
        LOGGER.info(messageSource.getMessage("dao.comment.getListOfPostsByPostId", new Object[]{id, comments},
                InstagramConstants.LOGGER_LOCALE));

        return comments;
    }
}
