package by.alex.bsuir.instagram.dao.rating.impl;

import by.alex.bsuir.instagram.dao.rating.RatingDao;
import by.alex.bsuir.instagram.entity.Rating;
import by.alex.bsuir.instagram.util.InstagramConstants;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("ratingDao")
public class RatingDaoImpl implements RatingDao {

    private final Logger LOGGER = Logger.getLogger(getClass());

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public long saveRating(Rating rating) {
        sessionFactory.getCurrentSession().save(rating);
        LOGGER.info(messageSource.getMessage("dao.rating.save", new Object[]{rating}, InstagramConstants.LOGGER_LOCALE));

        return rating.getRatingId();
    }

    @Override
    public void deleteRating(long id) {
        Rating rating = new Rating();
        rating.setRatingId(id);
        Rating mergedRating = (Rating) sessionFactory.getCurrentSession().merge(rating);
        sessionFactory.getCurrentSession().delete(mergedRating);
        LOGGER.info(messageSource.getMessage("dao.rating.delete", new Object[]{id}, InstagramConstants.LOGGER_LOCALE));
    }

    @Override
    public void updateRating(Rating rating) {
        Rating mergedRating = (Rating) sessionFactory.getCurrentSession().merge(rating);
        sessionFactory.getCurrentSession().update(mergedRating);
        LOGGER.info(messageSource.getMessage("dao.rating.update", new Object[]{rating}, InstagramConstants.LOGGER_LOCALE));
    }

    @Override
    public Rating getRating(long id) {
        Rating rating;
        rating = sessionFactory.getCurrentSession().get(Rating.class, id);
        LOGGER.info(messageSource.getMessage("dao.rating.getById", new Object[]{id}, InstagramConstants.LOGGER_LOCALE));

        return rating;
    }

    @Override
    public List<Rating> getListOfRatings() {
        List<Rating> ratings;
        ratings = sessionFactory.getCurrentSession().createCriteria(Rating.class).list();
        LOGGER.info(messageSource.getMessage("dao.rating.getList", new Object[]{ratings}, InstagramConstants.LOGGER_LOCALE));

        return ratings;
    }

    @Override
    public Rating getRatingBySenderAndPostId(long sender, long postId) {
        String posthql = "FROM  by.alex.bsuir.instagram.entity.Rating R WHERE R.sender.id = :sender " +
                "and R.post.postId = :postId";
        Query query = sessionFactory.getCurrentSession().createQuery(posthql);
        query.setParameter("sender", sender);
        query.setParameter("postId", postId);
        Rating rating = (Rating) query.uniqueResult();

        LOGGER.info(messageSource.getMessage("dao.rating.getRatingBySenderAndPostId", new Object[]{sender, postId},
                InstagramConstants.LOGGER_LOCALE));

        return rating;
    }

    @Override
    public long getRatingCount(long postId, String type) {
        String posthql = "select count(*) FROM  by.alex.bsuir.instagram.entity.Rating R " +
                "WHERE R.post.postId = :postId and R.type = :ratingType";
        Query query = sessionFactory.getCurrentSession().createQuery(posthql);
        query.setParameter("postId", postId);
        query.setParameter("ratingType", type);

        return (Long)query.uniqueResult();
    }

}
