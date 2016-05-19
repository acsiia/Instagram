package by.alex.bsuir.instagram.dao.country.impl;

import by.alex.bsuir.instagram.dao.country.CountryDao;
import by.alex.bsuir.instagram.entity.Country;
import by.alex.bsuir.instagram.util.InstagramConstants;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("countryDao")
public class CountryDaoImpl implements CountryDao {

    private final Logger LOGGER = Logger.getLogger(getClass());

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Country getCountry(long country) {
        Country entity;
        entity = sessionFactory.getCurrentSession().get(Country.class, country);
        LOGGER.info(messageSource.getMessage("dao.country.getById", new Object[]{country},
                InstagramConstants.LOGGER_LOCALE));

        return entity;
    }

    @Override
    public List<Country> getCountriesByLocale() {
        List<Country> countries;
        countries = sessionFactory.getCurrentSession().createCriteria(Country.class).list();
        LOGGER.info(messageSource.getMessage("dao.country.getList", new Object[]{countries},
                InstagramConstants.LOGGER_LOCALE));

        return countries;
    }
}
