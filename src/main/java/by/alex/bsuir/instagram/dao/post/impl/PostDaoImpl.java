package by.alex.bsuir.instagram.dao.post.impl;

import by.alex.bsuir.instagram.util.InstagramConstants;
import by.alex.bsuir.instagram.dao.post.PostDao;
import by.alex.bsuir.instagram.entity.Post;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("postDao")
public class PostDaoImpl implements PostDao {

    private final Logger LOGGER = Logger.getLogger(getClass());

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public long savePost(Post post) {
        sessionFactory.getCurrentSession().save(post);
        LOGGER.info(messageSource.getMessage("dao.post.save", new Object[]{post}, InstagramConstants.LOGGER_LOCALE));

        return post.getPostId();
    }

    @Override
    public void deletePost(long id) {
        Post post = new Post();
        post.setPostId(id);
        Post mergedPost = (Post) sessionFactory.getCurrentSession().merge(post);
        sessionFactory.getCurrentSession().delete(mergedPost);

        LOGGER.info(messageSource.getMessage("dao.post.delete", new Object[]{id},
                InstagramConstants.LOGGER_LOCALE));
    }

    @Override
    public void updatePost(Post post) {
        Post mergedPost = (Post) sessionFactory.getCurrentSession().merge(post);
        sessionFactory.getCurrentSession().update(mergedPost);
        LOGGER.info(messageSource.getMessage("dao.post.update", new Object[]{post}, InstagramConstants.LOGGER_LOCALE));
    }

    @Override
    public Post getPost(long id) {
        Post post;
        post = sessionFactory.getCurrentSession().get(Post.class, id);
        LOGGER.info(messageSource.getMessage("dao.post.getById", new Object[]{id}, InstagramConstants.LOGGER_LOCALE));

        return post;
    }

    @Override
    public List<Post> getListOfPosts() {
        List<Post> posts;
        posts = sessionFactory.getCurrentSession().createCriteria(Post.class).list();
        LOGGER.info(messageSource.getMessage("dao.post.getList", new Object[]{posts}, InstagramConstants.LOGGER_LOCALE));

        return posts;
    }

    @Override
    public List<Post> getListOfPostsByIdOfOwner(long id) {
        String posthql = "FROM  by.alex.bsuir.instagram.entity.Post P WHERE P.owner.id = :id";
        Query query = sessionFactory.getCurrentSession().createQuery(posthql);
        query.setParameter("id", id);
        List<Post> posts = query.list();

        LOGGER.info(messageSource.getMessage("dao.post.getListByOwnerId", new Object[]{id, posts},
                InstagramConstants.LOGGER_LOCALE));

        return posts;
    }
}
