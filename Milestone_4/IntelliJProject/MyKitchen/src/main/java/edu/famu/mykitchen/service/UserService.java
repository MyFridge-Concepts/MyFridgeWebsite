package edu.famu.mykitchen.service;


import com.google.api.core.ApiFuture;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import edu.famu.mykitchen.model.Ingredients;
import edu.famu.mykitchen.model.Recipe;
import edu.famu.mykitchen.model.RestUser;
import edu.famu.mykitchen.model.User;
import edu.famu.mykitchen.util.ApiResponse;
import edu.famu.mykitchen.util.PersonalInfo;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutionException;

@Service
public class UserService {

    private final Firestore firestore;
    private static final String USER_COLLECTION = "User";
    private static final String INGREDIENT_COLLECTION = "Ingredients";
    private static final String RECIPE_COLLECTION = "Recipe";

    protected final Log logger = LogFactory.getLog(getClass());

    public UserService(){
        this.firestore = FirestoreClient.getFirestore();
    }


    private User documentToUser(DocumentSnapshot document) throws ParseException, ExecutionException, InterruptedException {
        User users = null;
        logger.info("Document: " + document);
        logger.info("called");
        if (document.exists()) {
            users = new User();
            users.setUserId(document.getId());
            users.setUsername(document.getString("displayName"));
//            users.setUserInfo((PersonalInfo) document.get("userInfo"));
//            users.setUserInfo(null);
            users.setProfilePic(document.getString("profilePic"));
            users.setBio(document.getString("bio"));
            users.setPrivate(Boolean.TRUE.equals(document.getBoolean("isPrivate")));
            users.setVerified(Boolean.TRUE.equals(document.getBoolean("isVerified")));
            users.setAdministrator(Boolean.TRUE.equals(document.getBoolean("isAdministrator")));

//            Date joined = Objects.requireNonNull(document.getTimestamp("joinedOn")).toDate();
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            sdf.setTimeZone(TimeZone.getTimeZone("UTC-4"));
//
//            users.setJoinedOn(sdf.format(joined));
//            users.setJoinedOn(null);
            ApiFuture<QuerySnapshot> querySnapshot = firestore.collection(USER_COLLECTION).document(users.getUserId()).collection("following").get();
            users.setFollowing((ArrayList<User>) querySnapshot.get().toObjects(User.class));
            querySnapshot = firestore.collection(USER_COLLECTION).document(users.getUserId()).collection("followers").get();
            users.setFollowers((ArrayList<User>) querySnapshot.get().toObjects(User.class));
//            users.setFollowing(null);
//            users.setFollowers(null);
            ApiFuture<QuerySnapshot> querySnapshot1 = firestore.collection(USER_COLLECTION).document(users.getUserId()).collection("favoriteRecipes").get();
            users.setFavoriteRecipes((ArrayList<Recipe>) querySnapshot1.get().toObjects(Recipe.class));
            querySnapshot1 = firestore.collection(USER_COLLECTION).document(users.getUserId()).collection("uploadedRecipes").get();
            users.setUploadedRecipes((ArrayList<Recipe>) querySnapshot1.get().toObjects(Recipe.class));
//            ApiFuture<QuerySnapshot> querySnapshot2 = firestore.collection(USER_COLLECTION).document(users.getUserId()).collection("myFridge").get();
//            users.setMyFridge((ArrayList<Map<String, Ingredients>>) querySnapshot2.get().toObjects(Ingredients.class));
users.setMyFridge(null);
//            users.setFollowing((ArrayList<User>) document.get("following"));
//            users.setFollowers((ArrayList<User>) document.get("followers"));
//            users.setFavoriteRecipes((ArrayList<Recipe>) document.get("favoriteRecipes"));
//            users.setUploadedRecipes((ArrayList<Recipe>) document.get("uploadedRecipes"));
//            users.setMyFridge((Map<String, ArrayList<Ingredients>>) document.get("myFridge"));

        }
        logger.info("User: " + users);
        return users;
    }

    public List<User> getAllUsers() throws ExecutionException, InterruptedException {
        CollectionReference usersCollection = firestore.collection(USER_COLLECTION);
        ApiFuture<QuerySnapshot> querySnapshot = usersCollection.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.get().getDocuments();

        List<User> users = documents.isEmpty() ? null : new ArrayList<>();

        documents.forEach(document -> {
            User user = new User();
            try {
                user = (documentToUser(document));
            } catch (ParseException | ExecutionException | InterruptedException e) {
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

    public User deleteUserById(String userId) throws ParseException, ExecutionException, InterruptedException {
        DocumentReference usersRef = firestore.collection(USER_COLLECTION).document(userId);
        DocumentSnapshot userSnap = usersRef.get().get();
        User user = documentToUser(userSnap);
        usersRef.delete();
        return user;
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
                } catch (ExecutionException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
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
