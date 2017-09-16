package ch.raiffeisen.hackzurich.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class ImageReceipt {
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
}
