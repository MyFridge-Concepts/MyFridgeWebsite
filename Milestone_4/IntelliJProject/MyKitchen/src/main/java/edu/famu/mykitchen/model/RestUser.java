package edu.famu.mykitchen.model;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.database.annotations.Nullable;
import edu.famu.mykitchen.util.PersonalInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;



@Data
@NoArgsConstructor
public class RestUser extends AUser implements Serializable {
    private @Nullable ArrayList<DocumentReference> followers;
    private @Nullable ArrayList<DocumentReference> following;
    private @Nullable ArrayList<DocumentReference> favoriteRecipes;
    private @Nullable ArrayList<DocumentReference> uploadedRecipes;
    private @Nullable ArrayList<Map<String, Object>> myFridge;


    public RestUser(@javax.annotation.Nullable String userId, PersonalInfo userInfo, String profilePic, String bio, String username, boolean isPrivate, boolean isVerified, boolean isAdministrator, ArrayList<DocumentReference> followers, ArrayList<DocumentReference> following, ArrayList<DocumentReference> favoriteRecipes, ArrayList<DocumentReference> uploadedRecipes, ArrayList<Map<String, Object>> myFridge) {
        super(userId, userInfo, profilePic, bio, username, isPrivate, isVerified, isAdministrator);
        this.followers = followers;
        this.following = following;
        this.favoriteRecipes = favoriteRecipes;
        this.uploadedRecipes = uploadedRecipes;
        this.myFridge = myFridge;
    }


    public RestUser(User user) {
        super(user.getUserId(), user.getUserInfo(), user.getProfilePic(), user.getBio(), user.getUsername(), user.isPrivate(), user.isVerified(), user.isAdministrator());
        followers = new ArrayList<>();
        following = new ArrayList<>();
        favoriteRecipes = new ArrayList<>();
        uploadedRecipes = new ArrayList<>();
        myFridge = new ArrayList<>();
    }



}
