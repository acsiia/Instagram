package by.alex.bsuir.instagram.controller;

import by.alex.bsuir.instagram.dto.PostDTO;
import by.alex.bsuir.instagram.dto.UserDTO;
import by.alex.bsuir.instagram.jsonview.Views;
import by.alex.bsuir.instagram.service.AuthorizationService;
import by.alex.bsuir.instagram.service.post.PostService;
import by.alex.bsuir.instagram.validator.MessageContentValidator;
import by.alex.bsuir.instagram.validator.PostValidator;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping(value = "/post")
public class PostController {
    //TODO pagination

    @Autowired
    private MessageContentValidator messageContentValidator;

    @Autowired
    private PostService postService;

    @Autowired
    private AuthorizationService authorizationService;

    @Autowired
    private PostValidator postValidator;

    @RequestMapping(method = RequestMethod.GET)
    public String postPage(@RequestParam("postOwnerId") long id,
                           Map<String, Object> model) {
        PostDTO post = new PostDTO();
        post.setOwner(id);
        model.put("postForm", post);
        return "post/post";
    }

    @RequestMapping(value = "/addPost", method = RequestMethod.POST)
    public String addPost(@ModelAttribute("postForm") PostDTO post,
                                                      BindingResult result,
                                                      HttpServletRequest request) throws IOException {
        post.setPostContent(messageContentValidator.encodeMessage(post.getPostContent()));
        post.setPostContent(messageContentValidator.findLink(post.getPostContent()));
        postValidator.validate(post, result);

        if (result.hasErrors()) {
            return "redirect:"+ request.getHeader("Referer");
        }
        UserDTO authUser = authorizationService.getAuthUser();
        post.setLike(0L);
        post.setDislike(0L);
        post.setSender(authUser.getUserId());
        post.setImageByte(post.getPicture().getBytes());
        post.setId(postService.savePost(post));

        return "redirect:/users/user/" + post.getOwner();
    }

    @JsonView(Views.Comment.class)
    @RequestMapping(value = "/deletePost", method = RequestMethod.GET)
    public
    @ResponseBody
    void removePost(@RequestParam Long id) {
        UserDTO authUser = authorizationService.getAuthUser();
        PostDTO post = postService.getPost(id);

        if (authUser.getUserId().equals(post.getOwner()) || authUser.getUserId().equals(post.getSender())) {
            postService.deletePost(id);
        }
    }

}
