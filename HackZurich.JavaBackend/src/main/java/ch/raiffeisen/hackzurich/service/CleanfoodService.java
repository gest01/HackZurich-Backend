package ch.raiffeisen.hackzurich.service;

import ch.raiffeisen.hackzurich.controller.CleanfoodController;
import ch.raiffeisen.hackzurich.domain.CleanFoodImage;
import ch.raiffeisen.hackzurich.dto.Entry;
import ch.raiffeisen.hackzurich.repositories.CleanFoodRepository;
import ch.raiffeisen.hackzurich.service.firebase.FirebaseService;
import ch.raiffeisen.hackzurich.service.google.GoogleVisionClient;
import com.google.api.services.vision.v1.model.EntityAnnotation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@Component
public class CleanfoodService {
    private final Logger logger = LoggerFactory.getLogger(CleanfoodService.class);

    @Resource
    private CleanFoodRepository cleanFoodRepository;

    @Resource
    private GoogleVisionClient googleVisionClient;

    @Resource
    private FirebaseService firebaseService;


    public Long saveImage(byte [] imagedata ) {
        logger.info("Uploading image with size:" +imagedata.length);
        CleanFoodImage image = new CleanFoodImage();
        image.setImageData(imagedata);
        logger.info("Save image to the database");
        cleanFoodRepository.save(image);
        logger.info("Image DB ID: "+image.getId());
        return image.getId();
    }


    public List<EntityAnnotation> getGoogleLabelData(byte [] imagedata) throws IOException {
        String google_application_credentials = System.getenv("GOOGLE_APPLICATION_CREDENTIALS");
        logger.info("GOOGLE_APPLICATION_CREDENTIALS: "+google_application_credentials);

        logger.info("Call google vision api start");
        List<EntityAnnotation> entityAnnotations = googleVisionClient.labelImage(imagedata);
        logger.info("Call google vision api finish with hits: "+(entityAnnotations!=null ? entityAnnotations.size() : 0));
        return entityAnnotations;

    }

    public void createFirebaseEntry(List<EntityAnnotation> googleLabelData) {
        Entry e = new Entry();
        e.setGoogle(googleLabelData);
        e.setHealthscore(90);
        e.setImageUrl("blub");
        logger.info("Start firebase create entry");
        firebaseService.createEntry(e);
        logger.info("Finish firebase create entry");
    }

}
