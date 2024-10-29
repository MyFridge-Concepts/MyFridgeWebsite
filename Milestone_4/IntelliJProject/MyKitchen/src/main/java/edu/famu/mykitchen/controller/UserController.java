package edu.famu.mykitchen.controller;

import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import edu.famu.mykitchen.model.Recipe;
import edu.famu.mykitchen.model.RestUser;
import edu.famu.mykitchen.model.User;
import edu.famu.mykitchen.service.UserService;
import edu.famu.mykitchen.util.ApiResponse;
import edu.famu.mykitchen.util.PersonalInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

//http://localhost:8080/api/user
@RequestMapping("/api/user")
@RestController
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/")
    public ResponseEntity<ApiResponse<String>> createUser(@RequestBody HashMap<String, Object> user) {
        try {
            RestUser users = new RestUser();
            users.setUserId((String) user.get("userId"));
            users.setUsername((String) user.get("displayName"));
            users.setUserInfo((PersonalInfo) user.get("userInfo"));
            users.setProfilePic((String) user.get("profilePic"));
            users.setBio((String) user.get("bio"));
            users.setPrivate((Boolean) user.get("isPrivate"));
            users.setVerified((Boolean) user.get("isVerified"));
            users.setAdministrator((Boolean) user.get("isAdministrator"));

            ArrayList<User> list = (ArrayList<User>) user.get("followers");

            ArrayList<DocumentReference> followerRef = new ArrayList<>();
            if (!list.isEmpty()) {
                for (User followers : list) {
                    Firestore db = FirestoreClient.getFirestore();
                    followerRef.add(db.collection("followers").document(followers));
                }
            }
            users.setFollowers(followerRef);

            list = (ArrayList<User>) user.get("following");

            ArrayList<DocumentReference> followingRef = new ArrayList<>();
            if (!list.isEmpty()) {
                for (User following : list) {
                    Firestore db = FirestoreClient.getFirestore();
                    followingRef.add(db.collection("following").document(following));
                }
            }
            users.setFollowing(followingRef);

            ArrayList<Recipe> Rlist = (ArrayList<Recipe>) user.get("favoriteRecipes");

            ArrayList<DocumentReference> favoriteRecipesRef = new ArrayList<>();
            if (!Rlist.isEmpty()) {
                for (Recipe favoriteRecipes : Rlist) {
                    Firestore db = FirestoreClient.getFirestore();
                    favoriteRecipesRef.add(db.collection("favoriteRecipes").document(favoriteRecipes));
                }
            }
            users.setFavoriteRecipes(favoriteRecipesRef);

            Rlist = (ArrayList<Recipe>) user.get("uploadedRecipes");

            ArrayList<DocumentReference> uploadedRecipesRef = new ArrayList<>();
            if (!Rlist.isEmpty()) {
                for (Recipe uploadedRecipes : Rlist) {
                    Firestore db = FirestoreClient.getFirestore();
                    uploadedRecipesRef.add(db.collection("uploadedRecipes").document(uploadedRecipes));
                }
            }
            users.setUploadedRecipes(uploadedRecipesRef);

            ArrayList<String> Ilist = (ArrayList<String>) user.get("myFridge");

            ArrayList<DocumentReference> myFridgeRef = new ArrayList<>();
            if (!Ilist.isEmpty()) {
                for (String myFridge : Ilist) {
                    Firestore db = FirestoreClient.getFirestore();
                    myFridgeRef.add(db.collection("myFridge").document(myFridge));
                }
            }
            users.setMyFridge(myFridgeRef);


            String id = service.createUser(users);

            return ResponseEntity.status(201).body(new ApiResponse<>(true, "User created successfully", id, null));
        } catch (ParseException e) {
            return ResponseEntity.status(401).body(new ApiResponse<>(false, "Invalid date format", null, e));
        } catch (ExecutionException e) {
            return ResponseEntity.status(500).body(new ApiResponse<>(false, "Internal Server Error", null, e));
        }
    }




}
