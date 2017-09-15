package ch.raiffeisen.hackzurich.domain;

import javax.persistence.*;

/**
 * Created by simon on 15.09.2017.
 */
@Entity
public class CleanFoodImage {


    private Long id;
    private byte[] imageData;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Lob
    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }
}
