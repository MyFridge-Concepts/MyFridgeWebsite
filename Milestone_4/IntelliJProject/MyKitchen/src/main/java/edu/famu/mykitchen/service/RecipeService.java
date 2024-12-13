// Service for Recipe
package edu.famu.mykitchen.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import edu.famu.mykitchen.model.RestRecipe;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class RecipeService {
    private final Firestore firestore;
    private static final String RECIPE_COLLECTION = "Recipe";

    public RecipeService() {
        this.firestore = FirestoreClient.getFirestore();
    }

    public String createRecipe(RestRecipe recipe) throws ExecutionException, InterruptedException {
        ApiFuture<DocumentReference> future = firestore.collection(RECIPE_COLLECTION).add(recipe);
        return future.get().getId();
    }

    public RestRecipe getRecipeById(String id) throws ExecutionException, InterruptedException {
        DocumentSnapshot snapshot = firestore.collection(RECIPE_COLLECTION).document(id).get().get();
        return snapshot.exists() ? snapshot.toObject(RestRecipe.class) : null;
    }

    public List<RestRecipe> getAllRecipes() throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> future = firestore.collection(RECIPE_COLLECTION).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        List<RestRecipe> recipes = new ArrayList<>();
        for (QueryDocumentSnapshot document : documents) {
            recipes.add(document.toObject(RestRecipe.class));
        }
        return recipes;
    }

    public void updateRecipe(String id, RestRecipe recipe) throws ExecutionException, InterruptedException {
        firestore.collection(RECIPE_COLLECTION).document(id).set(recipe).get();
    }

    public void deleteRecipe(String id) throws ExecutionException, InterruptedException {
        firestore.collection(RECIPE_COLLECTION).document(id).delete().get();
    }
}
