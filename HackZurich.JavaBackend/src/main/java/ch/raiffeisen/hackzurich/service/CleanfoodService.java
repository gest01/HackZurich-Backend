package ch.raiffeisen.hackzurich.service;

import ch.raiffeisen.hackzurich.domain.CleanFoodImage;
import ch.raiffeisen.hackzurich.domain.ImageFood;
import ch.raiffeisen.hackzurich.dto.FoodFacts;
import ch.raiffeisen.hackzurich.repositories.CleanFoodImageRepository;
import ch.raiffeisen.hackzurich.repositories.ImageFoodRepository;
import ch.raiffeisen.hackzurich.service.fatsecret.FoodService;
import ch.raiffeisen.hackzurich.service.fatsecret.HealthCalculator;
import ch.raiffeisen.hackzurich.service.fatsecret.HealthInformation;
import ch.raiffeisen.hackzurich.service.firebase.FirebaseService;
import ch.raiffeisen.hackzurich.service.google.GoogleVisionClient;
import com.fatsecret.platform.model.CompactFood;
import com.fatsecret.platform.model.Food;
import com.fatsecret.platform.model.Serving;
import com.google.api.services.vision.v1.model.EntityAnnotation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@Component
public class CleanfoodService {
    private final Logger logger = LoggerFactory.getLogger(CleanfoodService.class);

    @Resource
    private CleanFoodImageRepository cleanFoodRepository;

    @Resource
    private ImageFoodRepository imageFoodRepository;

    @Resource
    private GoogleVisionClient googleVisionClient;

    @Resource
    private FirebaseService firebaseService;

    @Resource
    private FoodService foodService;

    @Resource
    private HealthCalculator healthCalculator;


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
        HealthInformation healthInformation = null;
        for (EntityAnnotation googleLabel : googleLabelData) {
            List<CompactFood> foodFacts = foodService.getFoodFacts(googleLabel.getDescription());
            if(foodFacts.size()>0) {
                List<Food> foodDetails = foodService.getFoodDetails(foodFacts);
                healthInformation = healthCalculator.calculateHealth(foodDetails);
                for (Food foodDetail : foodDetails) {
                    ImageFood imageFood = new ImageFood();
                    Serving serving = foodDetail.getServings().get(0);
                    imageFood.setCalories(serving.getCalories());
                    imageFood.setCleanFoodImage(cleanFoodImage);
                    imageFood.setFat(serving.getFat());
                    imageFood.setHealthScore(new BigDecimal(healthInformation.getHealthScore()));
                    imageFood.setSugar(serving.getSugar());
                    imageFood.setUrl(serving.getServingUrl());
                    imageFood.setLabel(googleLabel.getDescription());
                    imageFoodRepository.save(imageFood);
                }
                break;
            }
        }

        createFirebaseEntry(entryId, googleLabelData, healthInformation);
    }

    private List<EntityAnnotation> getGoogleLabelData(byte [] imagedata) throws IOException {
        String google_application_credentials = System.getenv("GOOGLE_APPLICATION_CREDENTIALS");
        logger.info("GOOGLE_APPLICATION_CREDENTIALS: "+google_application_credentials);

        logger.info("Call google vision api start");
        List<EntityAnnotation> entityAnnotations = googleVisionClient.labelImage(imagedata);
        logger.info("Call google vision api finish with hits: "+(entityAnnotations!=null ? entityAnnotations.size() : 0));
        return entityAnnotations;

    }

    private void createFirebaseEntry(String entryId, List<EntityAnnotation> googleLabelData, HealthInformation healthInformation) {
        FoodFacts foodFacts = new FoodFacts();
        foodFacts.setGoogle(googleLabelData);
        foodFacts.setHealthscore(healthInformation.getHealthScore().intValue());
        foodFacts.setHealthInformation(healthInformation);
        logger.info("Start firebase create entry");
        String key = entryId != null ? entryId : "";
        firebaseService.setFoodFacts(entryId, foodFacts);
        logger.info("Finish firebase create entry");
    }

}
