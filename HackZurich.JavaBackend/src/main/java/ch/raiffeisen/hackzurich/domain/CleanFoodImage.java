package ch.raiffeisen.hackzurich.domain;

import org.dom4j.tree.AbstractEntity;

import javax.persistence.*;

@Entity
public class CleanFoodImage {

    private byte[] imageData;
    private byte[] thumbnailData;


    private Long id;

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

    public byte[] getThumbnailData() {
        return thumbnailData;
    }

    @Lob
    public void setThumbnailData(byte[] thumbnailData) {
        this.thumbnailData = thumbnailData;
    }
}
