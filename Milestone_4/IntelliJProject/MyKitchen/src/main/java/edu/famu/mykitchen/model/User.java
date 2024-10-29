package edu.famu.mykitchen.model;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.annotation.DocumentId;
import com.google.firebase.database.annotations.Nullable;
import edu.famu.mykitchen.util.PersonalInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Map;

@Data
@NoArgsConstructor
public class User extends AUser {
    private @Nullable ArrayList<User> followers;
    private @Nullable ArrayList<User> following;
    private @Nullable ArrayList<Recipe> favoriteRecipes;
    private @Nullable ArrayList<Recipe> uploadedRecipes;
    private @Nullable ArrayList<Map<String, Ingredients>> myFridge;

    public User(@javax.annotation.Nullable String userId, PersonalInfo userInfo, String profilePic, String bio, String username, boolean isPrivate, boolean isVerified, boolean isAdministrator, Timestamp joinedOn, ArrayList<User> followers, ArrayList<User> following, ArrayList<Recipe> favoriteRecipes, ArrayList<Recipe> uploadedRecipes, ArrayList<Map<String, Ingredients>> myFridge) {
        super(userId, userInfo, profilePic, bio, username, isPrivate, isVerified, isAdministrator, joinedOn);
        this.followers = followers;
        this.following = following;
        this.favoriteRecipes = favoriteRecipes;
        this.uploadedRecipes = uploadedRecipes;
        this.myFridge = myFridge;
    }
}
