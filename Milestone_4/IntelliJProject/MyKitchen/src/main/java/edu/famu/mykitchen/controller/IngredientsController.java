package edu.famu.mykitchen.controller;

import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import edu.famu.mykitchen.model.Ingredients;
import edu.famu.mykitchen.model.Recipe;
import edu.famu.mykitchen.model.RestUser;
import edu.famu.mykitchen.model.User;
import edu.famu.mykitchen.service.IngredientsService;
import edu.famu.mykitchen.util.ApiResponse;
import edu.famu.mykitchen.util.PersonalInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

//http://localhost:8080/api/user

@RestController
@RequestMapping("/api/ingredients")
public class IngredientsController {
    private final IngredientsService service;

    public IngredientsController(IngredientsService service) {
        this.service = service;
    }

    @PostMapping("/")
    public ResponseEntity<ApiResponse<String>> createIngredient(@RequestBody HashMap<String, Object> ingredients) {
        try {
            Ingredients ingredient = new Ingredients();
            ingredient.setIngredientId((String) ingredients.get("ingredientId"));
            ingredient.setName((String) ingredients.get("name"));
            ingredient.setCategory((String) ingredients.get("category"));
            ingredient.setNutritionalInfo((Map<String, String>) ingredients.get("nutritionalInfo"));


            String id = service.createIngredient(ingredient);

            return ResponseEntity.status(201).body(new ApiResponse<>(true, "User created successfully", id, null));
        } catch (ExecutionException e) {
            return ResponseEntity.status(500).body(new ApiResponse<>(false, "Internal Server Error", null, e));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    @GetMapping("/")
    public ResponseEntity<ApiResponse<ArrayList<Ingredients>>> getAllIngredients() {
        try {
            ArrayList<Ingredients> ingredients = (ArrayList<Ingredients>) service.getAllIngredients();
            //ArrayList<User> users = new ArrayList<>();

            return ResponseEntity.status(200).body(new ApiResponse<>(true, "Users retrieved successfully", ingredients, null));
        } catch (ExecutionException e) {
            return ResponseEntity.status(500).body(new ApiResponse<>(false, "Internal Server Error", null, e));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{ingredientId}")
    public ResponseEntity<ApiResponse<Ingredients>> getIngredientById(@PathVariable String ingredientId) {
        try {
            Ingredients ingredient = service.getIngredientById(ingredientId);

            return ResponseEntity.status(200).body(new ApiResponse<>(true, "User retrieved successfully", ingredient, null));
        } catch (ExecutionException e) {
            return ResponseEntity.status(500).body(new ApiResponse<>(false, "Internal Server Error", null, e));
        } catch (InterruptedException e) {
            return ResponseEntity.status(500).body(new ApiResponse<>(false, "Internal Server Error", null, e));
        } catch (ParseException e) {
            return ResponseEntity.status(500).body(new ApiResponse<>(false, "Internal Server Error", null, e));
        }
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<ApiResponse<Ingredients>> getIngredientByName(@PathVariable String name) {
        try {
            Ingredients ingredient = service.getIngredientByName(name);

            return ResponseEntity.status(200).body(new ApiResponse<>(true, "User retrieved successfully", ingredient, null));
        } catch (ExecutionException e) {
            return ResponseEntity.status(500).body(new ApiResponse<>(false, "Internal Server Error", null, e));
        } catch (InterruptedException e) {
           return ResponseEntity.status(500).body(new ApiResponse<>(false, "Internal Server Error", null, e));
        } catch (ParseException e) {
            return ResponseEntity.status(500).body(new ApiResponse<>(false, "Internal Server Error", null, e));
        }
    }
}
