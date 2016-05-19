package by.alex.bsuir.instagram.converter.rating;

import by.alex.bsuir.instagram.dto.RatingDTO;
import by.alex.bsuir.instagram.entity.Post;
import by.alex.bsuir.instagram.entity.Rating;
import by.alex.bsuir.instagram.entity.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RatingDTOToRatingConverter implements Converter<RatingDTO, Rating> {
    @Override
    public Rating convert(RatingDTO ratingDTO) {
        Rating rating = new Rating();
        rating.setRatingId(ratingDTO.getRatingId());
        rating.setType(ratingDTO.getType().getType());

        User sender = new User();
        sender.setId(ratingDTO.getSender());
        rating.setSender(sender);
        Post post = new Post();
        post.setPostId(ratingDTO.getPost());
        rating.setPost(post);

        return rating;
    }
}
