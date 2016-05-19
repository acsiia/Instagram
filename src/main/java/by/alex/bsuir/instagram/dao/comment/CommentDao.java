package by.alex.bsuir.instagram.dao.comment;

import by.alex.bsuir.instagram.entity.Comment;

import java.util.List;

public interface CommentDao {
   long saveComment(Comment comment);
   void deleteComment(long id);
   void updateComment(Comment comment);
   Comment getComment(long id);
   List<Comment> getListOfComments();
   List<Comment> getListOfPostsByPostId(long id);
}
