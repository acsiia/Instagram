import by.alex.bsuir.instagram.dao.comment.CommentDao;
import by.alex.bsuir.instagram.entity.Comment;
import by.alex.bsuir.instagram.util.InstagramConstants;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:config/application-context.xml", "classpath:h2-config.xml"})
@Transactional
public class CommentTest {
    private final Logger LOGGER = Logger.getLogger(getClass());
    private Comment comment;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private CommentDao commentDao;

    @Before
    public void init() {
        comment = new Comment();
        comment.setCommentContent("Hello world!");

        LOGGER.info(messageSource.getMessage("test.comment.init", new Object[]{comment},
                InstagramConstants.LOGGER_LOCALE));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFailSaveComment() {
        LOGGER.info(messageSource.getMessage("test.comment.saveFail", null,
                InstagramConstants.LOGGER_LOCALE));

        commentDao.saveComment(null);
    }

    @Test
    public void testAddComment() {
        commentDao.saveComment(comment);
        Comment storedComment = commentDao.getComment(comment.getCommentId());
        Assert.assertNotNull(storedComment);

        LOGGER.info(messageSource.getMessage("test.comment.save", new Object[]{comment, storedComment},
                InstagramConstants.LOGGER_LOCALE));
    }

    @Test
    public void testUpdateComment() {
        commentDao.saveComment(comment);
        String commentContent = "Hi!";
        comment.setCommentContent(commentContent);
        commentDao.updateComment(comment);
        Comment updateComment = commentDao.getComment(comment.getCommentId());
        Assert.assertEquals(comment, updateComment);
        Assert.assertEquals(commentContent, updateComment.getCommentContent());

        LOGGER.info(messageSource.getMessage("test.comment.update", new Object[]{comment, comment.getCommentId()},
                InstagramConstants.LOGGER_LOCALE));
    }

    @Test
    public void testDeleteComment() {
        commentDao.saveComment(comment);
        commentDao.deleteComment(comment.getCommentId());
        comment = commentDao.getComment(comment.getCommentId());
        Assert.assertNull(comment);

        LOGGER.info(messageSource.getMessage("test.comment.delete",
                new Object[]{}, InstagramConstants.LOGGER_LOCALE));
    }
}
