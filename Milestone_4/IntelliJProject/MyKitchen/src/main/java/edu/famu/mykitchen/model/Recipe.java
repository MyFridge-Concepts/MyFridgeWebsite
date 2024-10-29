package edu.famu.mykitchen.model;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.annotation.DocumentId;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Map;

@Data
@NoArgsConstructor
public class Recipe extends ARecipe{
    @DocumentId
    private User userId;
    private Map<String, ArrayList<Ingredients>> ingredientsUsed;

    public Recipe(String recipeId, String name, String description, String instructions, String category, String imageUrl, String videoUrl, String prepTime, String cookTime, long ingredientCompletion, long rating, long views, boolean isPrivate, Timestamp publishDate, User userId, Map<String, ArrayList<Ingredients>> ingredientsUsed) {
        super(recipeId, name, description, instructions, category, imageUrl, videoUrl, prepTime, cookTime, ingredientCompletion, rating, views, isPrivate, publishDate);
        this.userId = userId;
        this.ingredientsUsed = ingredientsUsed;
    }
}
