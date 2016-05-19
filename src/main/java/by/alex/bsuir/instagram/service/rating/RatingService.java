package by.alex.bsuir.instagram.service.rating;

import by.alex.bsuir.instagram.dto.PostDTO;
import by.alex.bsuir.instagram.dto.RatingDTO;

import java.util.List;

public interface RatingService {
   long saveRating(RatingDTO ratingDTO);
   void deleteRating(long id);
   void updateRating(RatingDTO ratingDTO);
   RatingDTO getRatingById(long id);
   List<RatingDTO> getListOfRatings();
   PostDTO saveOrDeleteRating(PostDTO postDTO, RatingDTO ratingDTO);
   void setPostRatings(PostDTO postDTO);
}
