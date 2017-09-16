package ch.raiffeisen.hackzurich.dto;

import com.google.api.services.vision.v1.model.EntityAnnotation;

/**
 * Representation of a Firebase entry.
 *
 * Created by fabian on 16.09.2017.
 */
public class Entry {
    private String imageUrl;
    private Integer healthscore;
    private EntityAnnotation google;
    private Object nutrition;
    private String user;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getHealthscore() {
        return healthscore;
    }

    public void setHealthscore(Integer healthscore) {
        this.healthscore = healthscore;
    }
}
