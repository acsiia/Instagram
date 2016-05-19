package by.alex.bsuir.instagram.converter.rating;

import by.alex.bsuir.instagram.entity.Rating;
import by.alex.bsuir.instagram.dto.RatingDTO;
import by.alex.bsuir.instagram.util.enums.RatingTypeEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RatingToRatingDTOConverter implements Converter<Rating, RatingDTO> {
    @Override
    public RatingDTO convert(Rating rating) {
        RatingDTO ratingDTO = new RatingDTO();
        ratingDTO.setRatingId(rating.getRatingId());
        ratingDTO.setPost(rating.getPost().getPostId());
        ratingDTO.setSender(rating.getSender().getId());

        if (RatingTypeEnum.LIKE.getType().equals(rating.getType())) {
            ratingDTO.setType(RatingTypeEnum.LIKE);
        } else {
            ratingDTO.setType(RatingTypeEnum.DISLIKE);
        }

        return ratingDTO;
    }
}
