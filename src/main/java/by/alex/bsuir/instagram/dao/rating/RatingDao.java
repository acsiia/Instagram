package by.alex.bsuir.instagram.dao.rating;

import by.alex.bsuir.instagram.entity.Rating;

import java.util.List;

public interface RatingDao {
     long saveRating(Rating rating);
     void deleteRating(long id);
     void updateRating(Rating rating);
     Rating getRating(long id);
     List<Rating> getListOfRatings();
     Rating getRatingBySenderAndPostId(long sender, long postId);
     long getRatingCount(long postId, String type);
}
