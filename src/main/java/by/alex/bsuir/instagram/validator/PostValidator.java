package by.alex.bsuir.instagram.validator;

import by.alex.bsuir.instagram.dto.PostDTO;
import by.alex.bsuir.instagram.util.InstagramConstants;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PostValidator implements Validator {

    private final Logger LOGGER = Logger.getLogger(getClass());

    @Autowired
    private MessageSource messageSource;

    @Override
    public boolean supports(Class<?> clazz) {return false;}

    @Override
    public void validate(Object post, Errors errors) {
        PostDTO postDTO = (PostDTO)post;
        checkEmptyPost(postDTO, errors);
    }

    private void checkEmptyPost(PostDTO postDTO, Errors errors) {
        String postContent = postDTO.getPostContent().trim();
        if(postDTO.getPicture().isEmpty() && StringUtils.isBlank(postContent)) {
            errors.rejectValue("postContent", "validator.post.emptypost");

            LOGGER.warn(messageSource.getMessage("post.validator.isEmpty", null, InstagramConstants.LOGGER_LOCALE));
        }

        LOGGER.info(messageSource.getMessage("post.validator.isNotEmpty", null, InstagramConstants.LOGGER_LOCALE));
    }
}
