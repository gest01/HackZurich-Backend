package ch.raiffeisen.hackzurich.domain;

import org.dom4j.tree.AbstractEntity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class ImageFood {

    private String url;
    private BigDecimal fat;
    private BigDecimal calories;
    private BigDecimal sugar;
    private BigDecimal healthScore;
    private CleanFoodImage cleanFoodImage;
    private String label;

    private Long id;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    @ManyToOne(optional = false)
    @JoinColumn(name = "image_id", nullable = false, updatable = false)
    public CleanFoodImage getCleanFoodImage (){
        return cleanFoodImage;
    }

    public void setCleanFoodImage(CleanFoodImage cleanFoodImage) {
        this.cleanFoodImage = cleanFoodImage;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public BigDecimal getFat() {
        return fat;
    }

    public void setFat(BigDecimal fat) {
        this.fat = fat;
    }

    public BigDecimal getCalories() {
        return calories;
    }

    public void setCalories(BigDecimal calories) {
        this.calories = calories;
    }

    public BigDecimal getSugar() {
        return sugar;
    }

    public void setSugar(BigDecimal sugar) {
        this.sugar = sugar;
    }

    public BigDecimal getHealthScore() {
        return healthScore;
    }

    public void setHealthScore(BigDecimal healthScore) {
        this.healthScore = healthScore;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
