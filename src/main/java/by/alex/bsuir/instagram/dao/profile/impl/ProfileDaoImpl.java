package by.alex.bsuir.instagram.dao.profile.impl;

import by.alex.bsuir.instagram.entity.Profile;
import by.alex.bsuir.instagram.util.InstagramConstants;
import by.alex.bsuir.instagram.dao.profile.ProfileDao;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("profileDao")
public class ProfileDaoImpl implements ProfileDao {

    private final Logger LOGGER = Logger.getLogger(getClass());

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public long saveProfile(Profile profile) {
        sessionFactory.getCurrentSession().save(profile);
        LOGGER.info(messageSource.getMessage("dao.profile.save", new Object[]{profile}, InstagramConstants.LOGGER_LOCALE));

        return profile.getId();
    }

    @Override
    public void deleteProfile(long id) {
        Profile profile = new Profile();
        profile.setId(id);
        Profile mergedProfile = (Profile) sessionFactory.getCurrentSession().merge(profile);
        sessionFactory.getCurrentSession().delete(mergedProfile);

        LOGGER.info(messageSource.getMessage("dao.profile.delete", new Object[]{id}, InstagramConstants.LOGGER_LOCALE));
    }

    @Override
    public void updateProfile(Profile profile) {
        Profile mergedProfile = (Profile) sessionFactory.getCurrentSession().merge(profile);
        sessionFactory.getCurrentSession().update(mergedProfile);
        LOGGER.info(messageSource.getMessage("dao.profile.update", new Object[]{profile}, InstagramConstants.LOGGER_LOCALE));
    }

    @Override
    public Profile getProfile(long id) {
        Profile profile;
        profile = sessionFactory.getCurrentSession().get(Profile.class, id);
        LOGGER.info(messageSource.getMessage("dao.profile.getById", new Object[]{id}, InstagramConstants.LOGGER_LOCALE));

        return profile;
    }

    @Override
    public Profile getProfileByUserId(long id) {
        String posthql = "FROM  by.alex.bsuir.instagram.entity.Profile P WHERE P.user.id = :id";
        Query query = sessionFactory.getCurrentSession().createQuery(posthql);
        query.setParameter("id", id);
        Profile profile = (Profile) query.uniqueResult();
        LOGGER.info(messageSource.getMessage("dao.profile.getByUserId", new Object[]{id}, InstagramConstants.LOGGER_LOCALE));

        return profile;
    }

    @Override
    public List<Profile> getListOfProfiles() {
        List<Profile> profiles;
        profiles = sessionFactory.getCurrentSession().createCriteria(Profile.class).list();
        LOGGER.info(messageSource.getMessage("dao.profile.getList", new Object[]{profiles}, InstagramConstants.LOGGER_LOCALE));

        return profiles;
    }
}
