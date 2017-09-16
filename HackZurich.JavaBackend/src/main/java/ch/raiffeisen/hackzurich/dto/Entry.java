package ch.raiffeisen.hackzurich.dto;

/**
 * Representation of a Firebase entry.
 *
 * Created by fabian on 16.09.2017.
 */
public class Entry {
    private String imageUrl;
    private Integer healthscore;
    private Object google;
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
