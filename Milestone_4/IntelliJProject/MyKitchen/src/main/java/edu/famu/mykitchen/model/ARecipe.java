package edu.famu.mykitchen.model;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.annotation.DocumentId;
import com.google.firebase.database.annotations.Nullable;
import com.google.protobuf.util.Timestamps;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.ParseException;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class ARecipe {
    @DocumentId
    private @Nullable String recipeId;
    private String name;
    private String description;
    private String instructions;
    private String category;
    private String imageUrl;
    private String videoUrl;
    private String prepTime;
    private String cookTime;
    private long ingredientCompletion;
    private long rating;
    private long views;
    private boolean isPrivate;
    private Timestamp publishDate;


    public Timestamp getPublishDate() {
        return publishDate;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public void setPublishDate(String publishDate) throws ParseException {
        this.publishDate = Timestamp.fromProto(Timestamps.parse(publishDate));


    }


}
