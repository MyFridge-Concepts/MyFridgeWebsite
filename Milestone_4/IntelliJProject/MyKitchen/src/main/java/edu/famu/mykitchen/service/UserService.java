// Service for User
package edu.famu.mykitchen.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import edu.famu.mykitchen.model.RestUser;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class UserService {
    private final Firestore firestore;
    private static final String USER_COLLECTION = "User";

    public UserService() {
        this.firestore = FirestoreClient.getFirestore();
    }

    public String createUser(RestUser user) throws ExecutionException, InterruptedException {
        ApiFuture<DocumentReference> future = firestore.collection(USER_COLLECTION).add(user);
        return future.get().getId();
    }

    public RestUser getUserById(String id) throws ExecutionException, InterruptedException {
        DocumentSnapshot snapshot = firestore.collection(USER_COLLECTION).document(id).get().get();
        return snapshot.exists() ? snapshot.toObject(RestUser.class) : null;
    }

    public List<RestUser> getAllUsers() throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> future = firestore.collection(USER_COLLECTION).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        List<RestUser> users = new ArrayList<>();
        for (QueryDocumentSnapshot document : documents) {
            users.add(document.toObject(RestUser.class));
        }
        return users;
    }

    public void updateUser(String id, RestUser user) throws ExecutionException, InterruptedException {
        firestore.collection(USER_COLLECTION).document(id).set(user).get();
    }

    public void deleteUser(String id) throws ExecutionException, InterruptedException {
        firestore.collection(USER_COLLECTION).document(id).delete().get();
    }
}
