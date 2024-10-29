package edu.famu.mykitchen.model;

import com.google.cloud.Timestamp;
import com.google.firebase.database.annotations.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.units.qual.N;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class APosts {
    private String postId;
    private Timestamp createdAt;
    private @Nullable String caption;
    private @Nullable String recipeImgUrl;
    private long likesNum, commentsNum;
    private boolean visibility;

}
