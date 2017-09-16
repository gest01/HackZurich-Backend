package ch.raiffeisen.hackzurich.service.fatsecret;

import java.io.Serializable;

/**
 * Created by simon on 16.09.2017.
 */
public class HealthInformation implements Serializable {
    private Long sugar;
    private Long fat;
    private Long calories;
    private Long healthScore;

    public HealthInformation(Long sugar, Long fat, Long calories, Long healthScore) {
        this.sugar = sugar;
        this.fat = fat;
        this.calories = calories;
        this.healthScore = healthScore;
    }

    public Long getSugar() {
        return sugar;
    }

    public void setSugar(Long sugar) {
        this.sugar = sugar;
    }

    public Long getFat() {
        return fat;
    }

    public void setFat(Long fat) {
        this.fat = fat;
    }

    public Long getCalories() {
        return calories;
    }

    public void setCalories(Long calories) {
        this.calories = calories;
    }

    public Long getHealthScore() {
        return healthScore;
    }

    public void setHealthScore(Long healthScore) {
        this.healthScore = healthScore;
    }
}
