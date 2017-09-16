package ch.raiffeisen.hackzurich.dto;

import ch.raiffeisen.hackzurich.service.fatsecret.HealthInformation;
import com.fatsecret.platform.model.CompactRecipe;
import com.fatsecret.platform.model.Food;
import com.fatsecret.platform.model.Recipe;
import com.google.api.services.vision.v1.model.EntityAnnotation;

import java.util.List;

/**
 * Representation of a Firebase entry.
 *
 * Created by fabian on 16.09.2017.
 */
public class FoodFacts {
    private Integer healthscore;
    private List<EntityAnnotation> google;
    private Object nutritionFacts;
    private List<Food> foodDetails;
    private HealthInformation healthInformation;
    private List<CompactRecipe> recipes;

    public Integer getHealthscore() {
        return healthscore;
    }

    public void setHealthscore(Integer healthscore) {
        this.healthscore = healthscore;
    }

    public List<EntityAnnotation> getGoogle() {
        return google;
    }

    public void setGoogle(List<EntityAnnotation> google) {
        this.google = google;
    }

    public HealthInformation getHealthInformation() {
        return healthInformation;
    }

    public void setHealthInformation(HealthInformation healthInformation) {
        this.healthInformation = healthInformation;
    }

    public List<CompactRecipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<CompactRecipe> recipes) {
        this.recipes = recipes;
    }

    public List<Food> getFoodDetails() {
        return foodDetails;
    }

    public void setFoodDetails(List<Food> foodDetails) {
        this.foodDetails = foodDetails;
    }
}
