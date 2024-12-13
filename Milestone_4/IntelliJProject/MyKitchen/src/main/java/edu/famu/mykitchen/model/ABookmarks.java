package edu.famu.mykitchen.model;

public abstract class ABookmarks {
    @DocumentId
    private String id;
    private DocumentReference recipe;
    private DocumentReference user;
    private DocumentReference post;
}