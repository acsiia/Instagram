package by.alex.bsuir.instagram.service.post;

import by.alex.bsuir.instagram.dto.PostDTO;

import java.util.List;

public interface PostService {
   long savePost(PostDTO postDTO);
   void deletePost(long id);
   void updatePost(PostDTO postDTO);
   PostDTO getPost(long id);
   List<PostDTO> getListOfPosts();
   List<PostDTO> getListOfPostsByIdOfOwner(long id);
   List<PostDTO> getReversedListOfPostsByIdOfOwner(long id);
}
