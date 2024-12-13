package edu.famu.mykitchen.model;

public abstract class AIngredients {
    @DocumentId
    private String ingredientId;
    private String name;
    private String category;
    private Map<String, Number> nutritionalInfo;
}
