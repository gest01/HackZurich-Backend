package ch.raiffeisen.hackzurich.dto;

import ch.raiffeisen.hackzurich.domain.CleanFoodImage;
import ch.raiffeisen.hackzurich.domain.ImageFood;

import java.math.BigDecimal;

/**
 * Created by simon on 16.09.2017.
 */
public class ImageDetail {

    private Integer imageSize;
    private Integer thumbSize;
    private String url;
    private BigDecimal fat;
    private BigDecimal calories;
    private BigDecimal sugar;
    private BigDecimal healthScore;
    private String label;


    public Integer getImageSize() {
        return imageSize;
    }

    public void setImageSize(Integer imageSize) {
        this.imageSize = imageSize;
    }

    public Integer getThumbSize() {
        return thumbSize;
    }

    public void setThumbSize(Integer thumbSize) {
        this.thumbSize = thumbSize;
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

    public static ImageDetail from(CleanFoodImage image, ImageFood imageFood) {
        ImageDetail details = new ImageDetail();
        details.setImageSize(image.getImageData()!=null ? image.getImageData().length : null);
        details.setThumbSize(image.getThumbnailData()!=null ? image.getThumbnailData().length : null);
        details.setCalories(imageFood.getCalories());
        details.setFat(imageFood.getFat());
        details.setHealthScore(imageFood.getHealthScore());
        details.setLabel(imageFood.getLabel());
        details.setSugar(imageFood.getSugar());
        details.setUrl(imageFood.getUrl());

        return details;
    }

}
