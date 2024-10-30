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
        super(userId, userInfo, profilePic, bio, username, isPrivate, isVerified, isAdministrator);
        this.followers = followers;
        this.following = following;
        this.favoriteRecipes = favoriteRecipes;
        this.uploadedRecipes = uploadedRecipes;
        this.myFridge = myFridge;
    }

    /*public RestUser(@javax.annotation.Nullable String userId, String profilePic, String bio, String username, boolean isPrivate, boolean isVerified, boolean isAdministrator, ArrayList<DocumentReference> followers, ArrayList<DocumentReference> following, ArrayList<DocumentReference> favoriteRecipes, ArrayList<DocumentReference> uploadedRecipes, ArrayList<Map<String, DocumentReference>> myFridge) {
        super(userId, profilePic, bio, username, isPrivate, isVerified, isAdministrator);
        this.followers = followers;
        this.following = following;
        this.favoriteRecipes = favoriteRecipes;
        this.uploadedRecipes = uploadedRecipes;
        this.myFridge = myFridge;
    }*/

    // Custom serialization logic for DocumentReference
    private void writeObject(java.io.ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        // Serialize DocumentReference objects as their paths
        out.writeObject(followers.stream().map(DocumentReference::getPath).collect(Collectors.toList()));
        out.writeObject(following.stream().map(DocumentReference::getPath).collect(Collectors.toList()));
        out.writeObject(favoriteRecipes.stream().map(DocumentReference::getPath).collect(Collectors.toList()));
        out.writeObject(uploadedRecipes.stream().map(DocumentReference::getPath).collect(Collectors.toList()));
        out.writeObject(myFridge.stream().map(map -> map.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().getPath()))).collect(Collectors.toList()));
    }

    private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        Firestore db = FirestoreClient.getFirestore();
        // Deserialize paths back to DocumentReference objects
        followers = (ArrayList<DocumentReference>) ((List<String>) in.readObject()).stream().map(db::document).collect(Collectors.toList());
        following = (ArrayList<DocumentReference>) ((List<String>) in.readObject()).stream().map(db::document).collect(Collectors.toList());
        favoriteRecipes = (ArrayList<DocumentReference>) ((List<String>) in.readObject()).stream().map(db::document).collect(Collectors.toList());
        uploadedRecipes = (ArrayList<DocumentReference>) ((List<String>) in.readObject()).stream().map(db::document).collect(Collectors.toList());
        myFridge = (ArrayList<Map<String, DocumentReference>>) ((List<Map<String, String>>) in.readObject()).stream().map(map -> map.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> db.document(e.getValue())))).collect(Collectors.toList());
    }
}
