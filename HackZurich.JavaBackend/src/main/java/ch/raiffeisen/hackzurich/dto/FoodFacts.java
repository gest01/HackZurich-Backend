package ch.raiffeisen.hackzurich.dto;

import ch.raiffeisen.hackzurich.service.fatsecret.HealthInformation;
import com.google.api.services.vision.v1.model.EntityAnnotation;

import java.util.List;

/**
 * Representation of a Firebase entry.
 *
 * Created by fabian on 16.09.2017.
 */
public class FoodFacts {
    private Integer healthscore;
    List<EntityAnnotation> google;
    private Object nutritionFacts;
    private HealthInformation healthInformation;

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
}
