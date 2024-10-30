package edu.famu.mykitchen.service;


import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import edu.famu.mykitchen.model.Ingredients;
import edu.famu.mykitchen.model.RestUser;
import edu.famu.mykitchen.model.User;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class IngredientsService {

    private final Firestore firestore;

    private static final String INGREDIENT_COLLECTION = "Ingredients";


    protected final Log logger = LogFactory.getLog(getClass());

    public IngredientsService(){
        this.firestore = FirestoreClient.getFirestore();
    }


    private Ingredients documentToIngredients(DocumentSnapshot document) throws ParseException, ExecutionException, InterruptedException {
        Ingredients ingredients = null;
        logger.info("Document: " + document);
        logger.info("called");
        if (document.exists()) {
            ingredients = new Ingredients();
            ingredients.setIngredientId(document.getId());
            ingredients.setName(document.getString("name"));
            ingredients.setCategory(document.getString("category"));
            ingredients.setNutritionalInfo(null);
        }
        logger.info("Ingredient: " + ingredients);
        return ingredients;
    }


    public Ingredients updateIngredient(Ingredients ingredient) throws ExecutionException, InterruptedException {
        DocumentReference docRef = firestore.collection(INGREDIENT_COLLECTION).document(ingredient.getIngredientId());
        ApiFuture<WriteResult> result = docRef.set(ingredient);
        return ingredient;
    }

    public Ingredients deleteIngredient(String ingredientId) throws ExecutionException, InterruptedException, ParseException {
        DocumentReference docRef = firestore.collection(INGREDIENT_COLLECTION).document(ingredientId);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();
        Ingredients ingredient = null;
        if (document.exists()) {
            ingredient = documentToIngredients(document);
            ApiFuture<WriteResult> result = docRef.delete();
        }
        return ingredient;
    }

    public List<Ingredients> getAllIngredients() throws ExecutionException, InterruptedException {
        CollectionReference usersCollection = firestore.collection(INGREDIENT_COLLECTION);
        ApiFuture<QuerySnapshot> querySnapshot = usersCollection.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.get().getDocuments();

        List<Ingredients> ingredients = documents.isEmpty() ? null : new ArrayList<>();

        documents.forEach(document -> {
            Ingredients ingredient = new Ingredients();
            try {
                ingredient = (documentToIngredients(document));
            } catch (ParseException | ExecutionException | InterruptedException e) {
                throw new RuntimeException(e);
            }
            ingredients.add(ingredient);
        });
        return ingredients;
    }

    public Ingredients getIngredientById(String ingredientsId) throws ParseException, ExecutionException, InterruptedException {
        DocumentReference ingredientsRef = firestore.collection(INGREDIENT_COLLECTION).document(ingredientsId);
        DocumentSnapshot ingredientsSnap = ingredientsRef.get().get();
        return documentToIngredients(ingredientsSnap);
    }


    public Ingredients getIngredientByName(String name) throws ParseException, ExecutionException, InterruptedException {
        Query query = firestore.collection(INGREDIENT_COLLECTION).whereEqualTo("name", name);
        ApiFuture<QuerySnapshot> future = query.get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();

        List<Ingredients> ingredients = documents.isEmpty() ? null : new ArrayList<>();

        documents.forEach(document -> {
            Ingredients ingredient = null;

            if (document.exists()) {
                try {
                    ingredient = documentToIngredients(document);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                } catch (ExecutionException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                ingredients.add(ingredient);
            }
        });
        return ingredients.get(0);
    }

    public String createIngredient(Ingredients ingredient) throws ExecutionException, InterruptedException {
        ApiFuture<DocumentReference> writeResult = firestore.collection(INGREDIENT_COLLECTION).add(ingredient);
        DocumentReference rs = writeResult.get();
        return rs.getId();
    }

}
