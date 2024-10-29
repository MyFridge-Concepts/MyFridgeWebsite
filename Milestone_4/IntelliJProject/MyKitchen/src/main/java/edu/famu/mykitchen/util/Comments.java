package edu.famu.mykitchen.util;

import com.google.cloud.Timestamp;
import edu.famu.mykitchen.model.User;

public record Comments(String commentId, User userId, String text, Timestamp createdAt) {
    public Comments {
        if (userId == null) {
            throw new IllegalArgumentException("userId cannot be null");
        }
        if (text == null) {
            throw new IllegalArgumentException("text cannot be null");
        }
        if (createdAt == null) {
            throw new IllegalArgumentException("createdAt cannot be null");
        }
    }
}
