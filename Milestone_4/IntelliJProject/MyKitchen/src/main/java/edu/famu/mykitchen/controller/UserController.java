package edu.famu.mykitchen.controller;

import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import edu.famu.mykitchen.model.Ingredients;
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
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

//http://localhost:8080/api/user

@RestController
@RequestMapping("/api/user")
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
            users.setUsername((String) user.get("username"));
//            users.setUserInfo((PersonalInfo) user.get("userInfo"));
            users.setUserInfo(null);
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
                    followerRef.add(db.collection("followers").document(String.valueOf(followers)));
                }
            }
            users.setFollowers(followerRef);

            list = (ArrayList<User>) user.get("following");

            ArrayList<DocumentReference> followingRef = new ArrayList<>();
            if (!list.isEmpty()) {
                for (User following : list) {
                    Firestore db = FirestoreClient.getFirestore();
                    followingRef.add(db.collection("following").document(String.valueOf(following)));
                }
            }
            users.setFollowing(followingRef);

            ArrayList<Recipe> Rlist = (ArrayList<Recipe>) user.get("favoriteRecipes");

            ArrayList<DocumentReference> favoriteRecipesRef = new ArrayList<>();
            if (!Rlist.isEmpty()) {
                for (Recipe favoriteRecipes : Rlist) {
                    Firestore db = FirestoreClient.getFirestore();
                    favoriteRecipesRef.add(db.collection("favoriteRecipes").document(String.valueOf(favoriteRecipes)));
                }
            }
            users.setFavoriteRecipes(favoriteRecipesRef);

            Rlist = (ArrayList<Recipe>) user.get("uploadedRecipes");

            ArrayList<DocumentReference> uploadedRecipesRef = new ArrayList<>();
            if (!Rlist.isEmpty()) {
                for (Recipe uploadedRecipes : Rlist) {
                    Firestore db = FirestoreClient.getFirestore();
                    uploadedRecipesRef.add(db.collection("uploadedRecipes").document(String.valueOf(uploadedRecipes)));
                }
            }
            users.setUploadedRecipes(uploadedRecipesRef);

            ArrayList<String> Ilist = (ArrayList<String>) user.get("myFridge");

            ArrayList<Map<String, DocumentReference>> myFridgeRef = new ArrayList<>();
            if (!Ilist.isEmpty()) {
                for (String myFridge : Ilist) {
                    Firestore db = FirestoreClient.getFirestore();
                    myFridgeRef.add((Map<String, DocumentReference>) db.collection("myFridge").document(myFridge));
                }
            }
            users.setMyFridge(myFridgeRef);


            String id = service.createUser(users);

            return ResponseEntity.status(201).body(new ApiResponse<>(true, "User created successfully", id, null));
        } catch (ExecutionException e) {
            return ResponseEntity.status(500).body(new ApiResponse<>(false, "Internal Server Error", null, e));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    @GetMapping("/")
    public ResponseEntity<ApiResponse<ArrayList<User>>> getAllUsers() {
        try {
            ArrayList<User> users = (ArrayList<User>) service.getAllUsers();
            //ArrayList<User> users = new ArrayList<>();

            return ResponseEntity.status(200).body(new ApiResponse<>(true, "Users retrieved successfully", users, null));
        } catch (ExecutionException e) {
            return ResponseEntity.status(500).body(new ApiResponse<>(false, "Internal Server Error", null, e));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse<User>> getUserById(@PathVariable String userId) {
        try {
            User user = service.getUserById(userId);
            return ResponseEntity.status(200).body(new ApiResponse<>(true, "User retrieved successfully", user, null));
        } catch (ExecutionException e) {
            return ResponseEntity.status(500).body(new ApiResponse<>(false, "Internal Server Error", null, e));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            return ResponseEntity.status(400).body(new ApiResponse<>(false, "Invalid User ID", null, e));
        }
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<ApiResponse<User>> getUserByUsername(@PathVariable String username) {
        try {
            User user = service.getUserByUsername(username);
            return ResponseEntity.status(200).body(new ApiResponse<>(true, "User retrieved successfully", user, null));
        } catch (ExecutionException e) {
            return ResponseEntity.status(500).body(new ApiResponse<>(false, "Internal Server Error", null, e));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            return ResponseEntity.status(400).body(new ApiResponse<>(false, "Invalid Username", null, e));
        }
    }

    @PutMapping("/update/{username}")
public ResponseEntity<ApiResponse<String>> updateUser(@PathVariable String username, @RequestBody HashMap<String, Object> user) {
    try {
        RestUser newinfo = new RestUser();

        newinfo.setUserId((String) user.get("userId"));
        newinfo.setUsername((String) user.get("username"));
        newinfo.setUserInfo((PersonalInfo) user.get("userInfo"));
        newinfo.setProfilePic((String) user.get("profilePic"));
        newinfo.setBio((String) user.get("bio"));
        newinfo.setPrivate((Boolean) user.get("isPrivate"));
        newinfo.setVerified((Boolean) user.get("isVerified"));
        newinfo.setAdministrator((Boolean) user.get("isAdministrator"));

        ArrayList<User> list = (ArrayList<User>) user.get("followers");
        ArrayList<DocumentReference> followerRef = new ArrayList<>();
        if (list != null && !list.isEmpty()) {
            for (User followers : list) {
                DocumentReference follower = FirestoreClient.getFirestore().collection("followers").document(followers.getUserId());

                followerRef.add(follower);
            }
        }

        newinfo.setFollowers(followerRef);

        list = (ArrayList<User>) user.get("following");
        ArrayList<DocumentReference> followingRef = new ArrayList<>();
        if (list != null && !list.isEmpty()) {
            for (User following : list) {
                DocumentReference followingL = FirestoreClient.getFirestore().collection("following").document(following.getUserId());

                followingRef.add(followingL);
            }
        }
        newinfo.setFollowing(followingRef);

        ArrayList<Recipe> Rlist = (ArrayList<Recipe>) user.get("favoriteRecipes");
        ArrayList<DocumentReference> favoriteRecipesRef = new ArrayList<>();
        if (Rlist != null && !Rlist.isEmpty()) {
            for (Recipe favRecipes : Rlist) {
                DocumentReference Recipe = FirestoreClient.getFirestore().collection("favoriteRecipes").document(String.valueOf(favRecipes.getUserId()));

                favoriteRecipesRef.add(Recipe);
            }
        }
        newinfo.setFavoriteRecipes(favoriteRecipesRef);

        Rlist = (ArrayList<Recipe>) user.get("uploadedRecipes");
        ArrayList<DocumentReference> uploadedRecipesRef = new ArrayList<>();
        if (Rlist != null && !Rlist.isEmpty()) {
            for (Recipe uploadRecipe : Rlist) {
                DocumentReference uploads = FirestoreClient.getFirestore().collection("uploadedRecipe").document(String.valueOf(uploadRecipe.getUserId()));

                uploadedRecipesRef.add(uploads);
            }
        }
        newinfo.setUploadedRecipes(uploadedRecipesRef);

        ArrayList<Map<String, Ingredients>> Ilist = (ArrayList<Map<String, Ingredients>>) user.get("myFridge");
        ArrayList<Map<String, DocumentReference>> myFridgeRef = new ArrayList<>();
        if (Ilist != null && !Ilist.isEmpty()) {
            for (Map<String, Ingredients> myFridge : Ilist) {
                Map<String, DocumentReference> fridgeMap = FirestoreClient.getFirestore().collection("myFridge").document(String.valueOf(myFridge.get()));

                myFridgeRef.add(fridgeMap);
            }
        }
        newinfo.setMyFridge(myFridgeRef);

//        newinfo.setMyFridge(null);

        service.updateUser(newinfo);

        return ResponseEntity.status(200).body(new ApiResponse<>(true, "User updated successfully", null, null));
    } catch (Exception e) {
        return ResponseEntity.status(500).body(new ApiResponse<>(false, "Internal Server Error", null, e));
    }
}


    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse<User>> deleteUserById(@PathVariable String userId) {
        try {
            User user = service.deleteUserById(userId);
            return ResponseEntity.status(200).body(new ApiResponse<>(true, "User deleted successfully", user, null));
        } catch (ExecutionException e) {
            return ResponseEntity.status(500).body(new ApiResponse<>(false, "Internal Server Error", null, e));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            return ResponseEntity.status(400).body(new ApiResponse<>(false, "Invalid User ID", null, e));
        }
    }
}
