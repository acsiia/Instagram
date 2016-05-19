import by.alex.bsuir.instagram.dao.user.UserDao;
import by.alex.bsuir.instagram.entity.User;
import by.alex.bsuir.instagram.hash.PasswordHashEncoder;
import by.alex.bsuir.instagram.util.InstagramConstants;
import by.alex.bsuir.instagram.util.enums.UserRoleEnum;
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
public class UserTest {
    private final Logger LOGGER = Logger.getLogger(getClass());
    private User user;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordHashEncoder passwordHashEncoder;

    @Before
    public void init() {
        user = new User();
        user.setLogin("user3");
        user.setPassword(passwordHashEncoder.encode("123456"));
        user.setEmail("user@gmail.com");
        user.setRole(UserRoleEnum.USER.getRole());

        LOGGER.info(messageSource.getMessage("test.user.init", new Object[]{user}, InstagramConstants.LOGGER_LOCALE));
    }


    @Test(expected = IllegalArgumentException.class)
    public void testFailSaveUser() {
        userDao.saveUser(null);

        LOGGER.info(messageSource.getMessage("test.user.saveFail", null,
                InstagramConstants.LOGGER_LOCALE));
    }

    @Test
    public void testAddUser() {
        userDao.saveUser(user);
        User storedUser = userDao.getUser(user.getId());
        Assert.assertNotNull(storedUser);

        LOGGER.info(messageSource.getMessage("test.user.save", new Object[]{user}, InstagramConstants.LOGGER_LOCALE));
    }

    @Test
    public void testGetUser() {
        userDao.saveUser(user);
        User userByName = userDao.getUserByName(user.getLogin());
        User userById = userDao.getUser(userByName.getId());
        Assert.assertEquals(userByName, user);
        Assert.assertEquals(userById, user);

        LOGGER.info(messageSource.getMessage("test.user.get", new Object[]{userByName,userById},
                InstagramConstants.LOGGER_LOCALE));
    }

    @Test
    public void testUpdateUser() {
        userDao.saveUser(user);
        String email = "qwerty@mail.ru";
        user.setEmail(email);
        userDao.updateUser(user);
        User updatedUser = userDao.getUser(user.getId());
        Assert.assertEquals(updatedUser, user);
        Assert.assertEquals(email, updatedUser.getEmail());

        LOGGER.info(messageSource.getMessage("test.user.update", new Object[]{user, user.getId()},
                InstagramConstants.LOGGER_LOCALE));
    }

    @Test
    public void testDeleteUser() {
        userDao.saveUser(user);
        userDao.deleteUserById(user.getId());
        User deletedUser = userDao.getUser(user.getId());
        Assert.assertNull(deletedUser);

        LOGGER.info(messageSource.getMessage("test.user.delete", null, InstagramConstants.LOGGER_LOCALE));
    }
}
