package edu.famu.mykitchen.model;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.DocumentReference;
import com.google.firebase.database.annotations.Nullable;
import edu.famu.mykitchen.util.PersonalInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Map;

@Data
@NoArgsConstructor
public class RestUser extends AUser {
    private @Nullable ArrayList<DocumentReference> followers;
    private @Nullable ArrayList<DocumentReference> following;
    private @Nullable ArrayList<DocumentReference> favoriteRecipes;
    private @Nullable ArrayList<DocumentReference> uploadedRecipes;
    private @Nullable ArrayList<Map<String, DocumentReference>> myFridge;

//    public RestUser(@Nullable String userId, String displayName, String email, Timestamp createdAt, @Nullable ArrayList<DocumentReference> followers, @Nullable ArrayList<DocumentReference> following, @Nullable ArrayList<DocumentReference> favoriteRecipes, @Nullable ArrayList<DocumentReference> uploadedRecipes, @Nullable Map<String, ArrayList<DocumentReference>> myFridge) {
//        super(userId, displayName, email, createdAt);
//        this.followers = followers;
//        this.following = following;
//        this.favoriteRecipes = favoriteRecipes;
//        this.uploadedRecipes = uploadedRecipes;
//        this.myFridge = myFridge;
//    }


    public RestUser(@javax.annotation.Nullable String userId, PersonalInfo userInfo, String profilePic, String bio, String username, boolean isPrivate, boolean isVerified, boolean isAdministrator, Timestamp joinedOn, ArrayList<DocumentReference> followers, ArrayList<DocumentReference> following, ArrayList<DocumentReference> favoriteRecipes, ArrayList<DocumentReference> uploadedRecipes, ArrayList<Map<String, DocumentReference>> myFridge) {
        super(userId, userInfo, profilePic, bio, username, isPrivate, isVerified, isAdministrator, joinedOn);
        this.followers = followers;
        this.following = following;
        this.favoriteRecipes = favoriteRecipes;
        this.uploadedRecipes = uploadedRecipes;
        this.myFridge = myFridge;
    }
}
