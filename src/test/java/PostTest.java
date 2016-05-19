import by.alex.bsuir.instagram.dao.post.PostDao;
import by.alex.bsuir.instagram.entity.Post;
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
public class PostTest {
    private final Logger LOGGER = Logger.getLogger(getClass());
    private Post post;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private PostDao postDao;

    @Before
    public void init() {
        post = new Post();
        post.setImageBytes(null);
        post.setDislike(10L);
        post.setLike(15L);
        post.setPostContent("Hello world!");

        LOGGER.info(messageSource.getMessage("test.post.init", new Object[]{post}, InstagramConstants.LOGGER_LOCALE));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFailSavePost() {
        LOGGER.info(messageSource.getMessage("test.post.saveFail", null,
                InstagramConstants.LOGGER_LOCALE));

        postDao.savePost(null);
    }

    @Test
    public void testAddPost() {
        postDao.savePost(post);
        Post storedPost = postDao.getPost(post.getPostId());
        Assert.assertNotNull(storedPost);

        LOGGER.info(messageSource.getMessage("test.post.save", new Object[]{post, storedPost},
                InstagramConstants.LOGGER_LOCALE));
    }

    @Test
    public void testUpdatePost() {
        postDao.savePost(post);
        String postContent = "Hi!";
        post.setPostContent(postContent);
        postDao.updatePost(post);
        Post updatedPost = postDao.getPost(post.getPostId());
        Assert.assertEquals(post, updatedPost);
        Assert.assertEquals(postContent, updatedPost.getPostContent());

        LOGGER.info(messageSource.getMessage("test.post.update", new Object[]{post, post.getPostId()},
                InstagramConstants.LOGGER_LOCALE));
    }

    @Test
    public void testDeletePost() {
        postDao.savePost(post);
        postDao.deletePost(post.getPostId());
        post = postDao.getPost(post.getPostId());
        Assert.assertNull(post);

        LOGGER.info(messageSource.getMessage("test.post.delete",
                new Object[]{}, InstagramConstants.LOGGER_LOCALE));
    }
}
