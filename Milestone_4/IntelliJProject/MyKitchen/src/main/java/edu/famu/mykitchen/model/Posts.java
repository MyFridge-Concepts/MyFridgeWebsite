package edu.famu.mykitchen.model;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.annotation.DocumentId;
import com.google.firebase.database.annotations.Nullable;
import edu.famu.mykitchen.util.Comments;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
@Data
@NoArgsConstructor
public class Posts extends APosts {
    @DocumentId
    private @Nullable ArrayList<Comments> comments;
    private User userId;
    private @Nullable ArrayList<User> likedBy;

    public Posts(String postId, Timestamp createdAt, String caption, String recipeImgUrl, long likesNum, long commentsNum, boolean visibility, ArrayList<Comments> comments, User userId, ArrayList<User> likedBy) {
        super(postId, createdAt, caption, recipeImgUrl, likesNum, commentsNum, visibility);
        this.comments = comments;
        this.userId = userId;
        this.likedBy = likedBy;
    }
}
