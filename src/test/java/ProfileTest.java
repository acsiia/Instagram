import by.alex.bsuir.instagram.dao.profile.ProfileDao;
import by.alex.bsuir.instagram.entity.Profile;
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

import java.sql.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:config/application-context.xml", "classpath:h2-config.xml"})
@Transactional
public class ProfileTest {
    private final Logger LOGGER = Logger.getLogger(getClass());
    private Profile profile;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private ProfileDao profileDao;

    @Before
    public void init() {
        profile = new Profile();
        profile.setBirthday(new Date(100000));
        profile.setFirstName("Max");
        profile.setSurname("Petrenko");
        profile.setSecondName("Vitalievich");
        profile.setSex("male");
//        profile.setCountry("Belarus");
        profile.setCity("Minsk");

        LOGGER.info(messageSource.getMessage("test.profile.init", new Object[]{profile},
                InstagramConstants.LOGGER_LOCALE));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFailSaveProfile() {
        LOGGER.info(messageSource.getMessage("test.profile.saveFail", null,
                InstagramConstants.LOGGER_LOCALE));

        profileDao.saveProfile(null);
    }

    @Test
    public void testSaveProfile() {
        profileDao.saveProfile(profile);
        Profile storedProfile = profileDao.getProfile(profile.getId());
        Assert.assertNotNull(storedProfile);

        LOGGER.info(messageSource.getMessage("test.profile.save", new Object[]{profile, profile.getId()},
                InstagramConstants.LOGGER_LOCALE));
    }

    @Test
    public void testUpdateProfile() {
        profileDao.saveProfile(profile);
        String firstName = "Vlad";
        profile.setFirstName(firstName);
        profileDao.updateProfile(profile);
        Profile updatedProfile = profileDao.getProfile(profile.getId());
        Assert.assertEquals(profile,updatedProfile);
        Assert.assertEquals(firstName,updatedProfile.getFirstName());

        LOGGER.info(messageSource.getMessage("test.profile.update", new Object[]{profile, profile.getId()},
                InstagramConstants.LOGGER_LOCALE));
    }

    @Test
    public void testDeleteProfile() {
        profileDao.saveProfile(profile);
        profileDao.deleteProfile(profile.getId());
        profile = profileDao.getProfile(profile.getId());
        Assert.assertNull(profile);

        LOGGER.info(messageSource.getMessage("test.profile.delete", null, InstagramConstants.LOGGER_LOCALE));
    }
}
