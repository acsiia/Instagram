package by.alex.bsuir.instagram.controller;

import by.alex.bsuir.instagram.dto.PostDTO;
import by.alex.bsuir.instagram.dto.UserDTO;
import by.alex.bsuir.instagram.jsonview.Views;
import by.alex.bsuir.instagram.service.AuthorizationService;
import by.alex.bsuir.instagram.service.post.PostService;
import by.alex.bsuir.instagram.service.rating.RatingService;
import by.alex.bsuir.instagram.util.enums.RatingTypeEnum;
import by.alex.bsuir.instagram.dto.RatingDTO;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/rating")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @Autowired
    private AuthorizationService authorizationService;

    @Autowired
    private PostService postService;

    @JsonView(Views.Rating.class)
    @RequestMapping(value = "/setLike", method = RequestMethod.POST)
    public @ResponseBody
    PostDTO setLike(@RequestBody RatingDTO rating) {
        return setRating(RatingTypeEnum.LIKE, rating);
    }

    @JsonView(Views.Rating.class)
    @RequestMapping(value = "/setDislike", method = RequestMethod.POST)
    public @ResponseBody PostDTO setDislike(@RequestBody RatingDTO rating) {
        return setRating(RatingTypeEnum.DISLIKE, rating);
    }

    private PostDTO setRating(RatingTypeEnum ratingTypeEnum, RatingDTO rating) {
        UserDTO authUser = authorizationService.getAuthUser();
        rating.setSender(authUser.getUserId());
        rating.setType(ratingTypeEnum);
        PostDTO post = postService.getPost(rating.getPost());
        post = ratingService.saveOrDeleteRating(post, rating);
        postService.updatePost(post);

        return post;
    }
}
