package by.alex.bsuir.instagram.hash;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DataAccessResourceFailureException;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component("passwordEncoder")
public class PasswordHashEncoder implements PasswordEncoder {

    public static final int SALT_LENGTH = 16;
    public static final int SALT_PLACE = 73;

    @Override
    public String encode(CharSequence rawPass) {
        try {
            String saltStr = randomSalt();
            return encrypt((String)rawPass, saltStr);
        } catch (Exception e) {
            throw new DataAccessResourceFailureException("Failed to encode password.", e);
        }
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        try {
            String saltStr = extractSaltFromHash(encodedPassword);
            return encrypt((String)rawPassword, saltStr).equals(encodedPassword);
        } catch (Exception e) {
            throw new DataAccessResourceFailureException("Failed to validate password.", e);
        }
    }

    public String encrypt(String pass, String salt) {

        String saltPassStr = addSaltToPassword(pass, salt);
        String hash = getSHA(saltPassStr);
        return addSaltToHash(hash, salt);
    }

    public String getSHA(String raw) {
        return DigestUtils.sha512Hex(raw);
    }

    public String randomSalt() {
        return RandomStringUtils.random(SALT_LENGTH, "abcdef0123456789");
    }

    public String addSaltToPassword(String password, String salt) {
        return new StringBuilder(password).append(salt).toString();
    }

    public String addSaltToHash(String hash, String salt) {
        String part1 = StringUtils.substring(hash, 0, SALT_PLACE);
        String part2 = StringUtils.substring(hash, SALT_PLACE);

        return new StringBuilder(part1).append(salt).append(part2).toString();
    }

    public String extractSaltFromHash(String hash) {
        return StringUtils.substring(hash, SALT_PLACE, SALT_PLACE + SALT_LENGTH);
    }


}
