package by.alex.bsuir.instagram.converter.post;

import by.alex.bsuir.instagram.dto.PostDTO;
import by.alex.bsuir.instagram.util.InstagramConstants;
import by.alex.bsuir.instagram.entity.Post;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import java.text.SimpleDateFormat;
import java.util.TimeZone;


@Component
public class PostToPostDTOConverter implements Converter<Post, PostDTO> {

    private final Logger LOGGER = Logger.getLogger(getClass());

    @Autowired
    private MessageSource messageSource;

    @Override
    public PostDTO convert(Post post) {
        if (post == null) {
            return null;
        }
        PostDTO postDTO = new PostDTO();
        postDTO.setId(post.getPostId());
        postDTO.setDislike(post.getDislike());
        postDTO.setLike(post.getLike());
        postDTO.setPostContent(post.getPostContent());
        postDTO.setImageByte(post.getImageBytes());
        postDTO.setOwner(post.getOwner().getId());
        postDTO.setSender(post.getSender().getId());

        convertTimestampToString(post, postDTO);

        LOGGER.info(messageSource.getMessage("converter.convert",
                new Object[]{"Post", "PostDTO", post, postDTO}, InstagramConstants.LOGGER_LOCALE));

        return postDTO;
    }

    private void convertTimestampToString(Post post, PostDTO postDTO) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(InstagramConstants.DATE_FORMAT);
        dateFormat.setTimeZone(TimeZone.getTimeZone(InstagramConstants.TIME_ZONE));
        String date = dateFormat.format(post.getDateDispatch());
        postDTO.setDateDispatch(date);
    }
}
