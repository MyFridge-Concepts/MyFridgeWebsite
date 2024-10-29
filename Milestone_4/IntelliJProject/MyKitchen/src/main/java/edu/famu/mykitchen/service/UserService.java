package edu.famu.mykitchen.service;


import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import edu.famu.mykitchen.model.Ingredients;
import edu.famu.mykitchen.model.Recipe;
import edu.famu.mykitchen.model.RestUser;
import edu.famu.mykitchen.model.User;
import edu.famu.mykitchen.util.PersonalInfo;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
public class UserService {

    private Firestore firestore;
    private static final String USER_COLLECTION = "User";
    private static final String INGREDIENT_COLLECTION = "Ingredients";
    private static final String RECIPE_COLLECTION = "Recipe";

    public UserService(){
        this.firestore = FirestoreClient.getFirestore();
    }

//    public User documentSnapshotToUser(DocumentSnapshot document) {
//        if (document.exists()) {
//            return document.toObject(User.class);
//        }
//        return null;
//    }
//
//    public List<User> getAllUsers() throws ExecutionException, InterruptedException {
//        CollectionReference userCollection = firestore.collection("Users");
//        ApiFuture<QuerySnapshot> future = userCollection.get();
//        List<User> userList = new ArrayList<>();
//        for (DocumentSnapshot document : future.get().getDocuments()) {
//            User user = documentSnapshotToUser(document);
//            if (user != null) {
//                userList.add(user);
//            }
//        }
//        return userList;
//    }
//
//    public User getUserById(String userId) throws ExecutionException, InterruptedException {
//        CollectionReference userCollection = firestore.collection("User");
//        ApiFuture<DocumentSnapshot> future = userCollection.document(userId).get();
//        DocumentSnapshot document = future.get();
//        return documentSnapshotToUser(document);
//    }

    private User documentToUser(DocumentSnapshot document) throws ParseException {
        User users = null;

        if (document.exists()) {
            users = new User();
            users.setUserId(document.getId());
            users.setUsername(document.getString("displayName"));
            users.setUserInfo((PersonalInfo) document.get("userInfo"));
            users.setProfilePic(document.getString("profilePic"));
            users.setBio(document.getString("bio"));
            users.setPrivate(document.getBoolean("isPrivate"));
            users.setVerified(document.getBoolean("isVerified"));
            users.setAdministrator(document.getBoolean("isAdministrator"));
            users.setJoinedOn(document.getTimestamp("joinedOn").toString());
            users.setFollowing(null);
            users.setFollowers(null);
            users.setFavoriteRecipes(null);
            users.setUploadedRecipes(null);
            users.setMyFridge(null);

//            users.setFollowing((ArrayList<User>) document.get("following"));
//            users.setFollowers((ArrayList<User>) document.get("followers"));
//            users.setFavoriteRecipes((ArrayList<Recipe>) document.get("favoriteRecipes"));
//            users.setUploadedRecipes((ArrayList<Recipe>) document.get("uploadedRecipes"));
//            users.setMyFridge((Map<String, ArrayList<Ingredients>>) document.get("myFridge"));

        }
        return users;
    }

    public List<User> getAllUsers() throws ExecutionException, InterruptedException {
        CollectionReference usersCollection = firestore.collection(USER_COLLECTION);
        ApiFuture<QuerySnapshot> querySnapshot = usersCollection.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.get().getDocuments();

        List<User> users = documents.isEmpty() ? null : new ArrayList<>();

        documents.forEach(document -> {
            User user = null;
            try {
                user = (documentToUser(document));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            users.add(user);
        });
        return users;
    }

    public User getUserById(String userId) throws ParseException, ExecutionException, InterruptedException {
        DocumentReference usersRef = firestore.collection(USER_COLLECTION).document(userId);
        DocumentSnapshot userSnap = usersRef.get().get();
        return documentToUser(userSnap);
    }


    public User getUserByUsername(String username) throws ParseException, ExecutionException, InterruptedException {
        Query query = firestore.collection(USER_COLLECTION).whereEqualTo("username", username);
        ApiFuture<QuerySnapshot> future = query.get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();

        List<User> users = documents.isEmpty() ? null : new ArrayList<>();

        documents.forEach(document -> {
            User user = null;

            if (document.exists()) {
                try {
                    user = documentToUser(document);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }

                users.add(user);
            }
        });
        return users.get(0);
    }

    public String createUser(RestUser user) throws ExecutionException, InterruptedException {
        ApiFuture<DocumentReference> writeResult = firestore.collection(USER_COLLECTION).add(user);
        DocumentReference rs = writeResult.get();
        return rs.getId();
    }

}
