package edu.famu.mykitchen.model;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.annotation.DocumentId;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Map;

@Data
@NoArgsConstructor
public class RestRecipe extends ARecipe {
    @DocumentId

    private DocumentReference userId;
    private Map<String, ArrayList<DocumentReference>> ingredientsUsed;

    public RestRecipe(String recipeId, String name, String description, String instructions, String category, String imageUrl, String videoUrl, String prepTime, String cookTime, long ingredientCompletion, long rating, long views, boolean isPrivate, Timestamp publishDate, DocumentReference userId, Map<String, ArrayList<DocumentReference>> ingredientsUsed) {
        super(recipeId, name, description, instructions, category, imageUrl, videoUrl, prepTime, cookTime, ingredientCompletion, rating, views, isPrivate, publishDate);
        this.userId = userId;
        this.ingredientsUsed = ingredientsUsed;
    }
}
