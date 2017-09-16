package ch.raiffeisen.hackzurich.dto;

import com.google.api.services.vision.v1.model.EntityAnnotation;

import java.util.List;

/**
 * Representation of a Firebase entry.
 *
 * Created by fabian on 16.09.2017.
 */
public class Entry {
    private String imageUrl;
    FoodFacts foodFacts;
    private Object nutritionFacts;
    private User user;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public FoodFacts getFoodFacts() {
        return foodFacts;
    }

    public void setFoodFacts(FoodFacts foodFacts) {
        this.foodFacts = foodFacts;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
