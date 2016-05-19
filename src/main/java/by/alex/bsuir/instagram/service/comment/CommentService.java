package by.alex.bsuir.instagram.service.comment;

import by.alex.bsuir.instagram.dto.CommentDTO;

import java.util.List;

public interface CommentService {
    long saveComment(CommentDTO commentDTO);
    void deleteComment(long id);
    void updateComment(CommentDTO commentDTO);
    CommentDTO getComment(long id);
    List<CommentDTO> getListOfComments();
    List<CommentDTO> getListOfPostsByPostId(long id);
}
