package edu.famu.mykitchen.model;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.DocumentReference;
import com.google.firebase.database.annotations.Nullable;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
public class RestPosts extends APosts{
    private @Nullable ArrayList<DocumentReference> comments;
    private DocumentReference userId;
    private @Nullable ArrayList<DocumentReference> likedBy;

    public RestPosts(String postId, Timestamp createdAt, String caption, String recipeImgUrl, long likesNum, long commentsNum, boolean visibility, ArrayList<DocumentReference> comments, DocumentReference userId, ArrayList<DocumentReference> likedBy) {
        super(postId, createdAt, caption, recipeImgUrl, likesNum, commentsNum, visibility);
        this.comments = comments;
        this.userId = userId;
        this.likedBy = likedBy;
    }
}
