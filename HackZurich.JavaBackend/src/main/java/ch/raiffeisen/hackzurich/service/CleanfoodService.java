package ch.raiffeisen.hackzurich.service;

import ch.raiffeisen.hackzurich.domain.CleanFoodImage;
import ch.raiffeisen.hackzurich.dto.FoodFacts;
import ch.raiffeisen.hackzurich.repositories.CleanFoodImageRepository;
import ch.raiffeisen.hackzurich.service.fatsecret.FoodService;
import ch.raiffeisen.hackzurich.service.firebase.FirebaseService;
import ch.raiffeisen.hackzurich.service.google.GoogleLanguageClient;
import ch.raiffeisen.hackzurich.service.google.GoogleVisionClient;
import com.google.api.services.vision.v1.model.EntityAnnotation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@Component
public class CleanfoodService {
    private final Logger logger = LoggerFactory.getLogger(CleanfoodService.class);

    @Resource
    private CleanFoodImageRepository cleanFoodRepository;

    @Resource
    private GoogleVisionClient googleVisionClient;

    @Resource
    private GoogleLanguageClient googleLanguageClient;

    @Resource
    private FirebaseService firebaseService;

    @Resource
    private FoodService foodService;


    public Long saveImage(byte [] imagedata, byte [] thumbnaildata ) {
        logger.info("Uploading image with size:" +imagedata.length);
        CleanFoodImage image = new CleanFoodImage();
        image.setImageData(imagedata);
        image.setThumbnailData(thumbnaildata);
        logger.info("Save image to the database");
        cleanFoodRepository.save(image);
        logger.info("Image DB ID: "+image.getId());
        return image.getId();
    }

    @Async
    public void analyze(Long imageId, String entryId) throws IOException {
        CleanFoodImage cleanFoodImage = cleanFoodRepository.findOne(imageId);
        List<EntityAnnotation> googleLabelData = getGoogleLabelData(cleanFoodImage.getImageData());
        createFirebaseEntry(entryId, googleLabelData);
    }

    @Async
    public void detectText(Long imageId, String entryId) throws IOException {
        CleanFoodImage cleanFoodImage = cleanFoodRepository.findOne(imageId);
        List<EntityAnnotation> googleTextData = getGoogleTextData(cleanFoodImage.getImageData());
        createFirebaseEntry(entryId, googleTextData);
    }

    private List<EntityAnnotation> getGoogleLabelData(byte [] imagedata) throws IOException {
        String google_application_credentials = System.getenv("GOOGLE_APPLICATION_CREDENTIALS");
        logger.info("GOOGLE_APPLICATION_CREDENTIALS: "+google_application_credentials);

        logger.info("Call google vision api with label data detection start");
        List<EntityAnnotation> entityAnnotations = googleVisionClient.labelImage(imagedata);
        logger.info("Call google vision api finish with hits: "+(entityAnnotations!=null ? entityAnnotations.size() : 0));
        return entityAnnotations;

    }

    private List<EntityAnnotation> getGoogleTextData(byte [] imagedata) throws IOException {
        String google_application_credentials = System.getenv("GOOGLE_APPLICATION_CREDENTIALS");
        logger.info("GOOGLE_APPLICATION_CREDENTIALS: "+google_application_credentials);

        logger.info("Call google vision api with text detection start");
        List<EntityAnnotation> entityAnnotations = googleVisionClient.detectText(imagedata);
        logger.info("Call google vision api finish with hits: "+(entityAnnotations!=null ? entityAnnotations.size() : 0));


//        googleLanguageClient.determineCategory();

        return entityAnnotations;

    }

    private void createFirebaseEntry(String entryId, List<EntityAnnotation> googleLabelData) {
        FoodFacts foodFacts = new FoodFacts();
        foodFacts.setGoogle(googleLabelData);
        foodFacts.setHealthscore(90);
        logger.info("Start firebase create entry");
        String key = entryId != null ? entryId : "";
        firebaseService.setFoodFacts(entryId, foodFacts);
        logger.info("Finish firebase create entry");
    }

}
