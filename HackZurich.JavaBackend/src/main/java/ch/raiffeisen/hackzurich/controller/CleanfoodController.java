package ch.raiffeisen.hackzurich.controller;

import ch.raiffeisen.hackzurich.domain.CleanFoodImage;
import ch.raiffeisen.hackzurich.dto.Entry;
import ch.raiffeisen.hackzurich.dto.FoodFacts;
import ch.raiffeisen.hackzurich.repositories.CleanFoodRepository;
import ch.raiffeisen.hackzurich.repositories.PersonRepository;
import ch.raiffeisen.hackzurich.service.firebase.FirebaseService;
import ch.raiffeisen.hackzurich.service.google.GoogleVisionClient;
import com.google.api.services.vision.v1.model.EntityAnnotation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.websocket.server.PathParam;
import java.io.*;
import java.util.List;

@RestController
@RequestMapping("/api/cleanfood/image")
public class CleanfoodController {
    private final Logger logger = LoggerFactory.getLogger(CleanfoodController.class);

    @Resource
    private CleanFoodRepository cleanFoodRepository;


    @Resource
    private GoogleVisionClient googleVisionClient;

    @Resource
    private FirebaseService firebaseService;


    @PostMapping("/uploadAndAnalyze")
    public Long handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        try {
            logger.info("Uploading image with size:" +file.getBytes().length);
            CleanFoodImage image = new CleanFoodImage();
            image.setImageData(file.getBytes());
            logger.info("Save image to the database");
            cleanFoodRepository.save(image);
            logger.info("Image DB ID: "+image.getId());

            String google_application_credentials = System.getenv("GOOGLE_APPLICATION_CREDENTIALS");
            logger.info("GOOGLE_APPLICATION_CREDENTIALS: "+google_application_credentials);

            logger.info("Call google vision api start");
            List<EntityAnnotation> entityAnnotations = googleVisionClient.labelImage(file.getBytes());
            logger.info("Call google vision api finish with hits: "+(entityAnnotations!=null ? entityAnnotations.size() : 0));

            FoodFacts foodFacts = new FoodFacts();
            foodFacts.setGoogle(entityAnnotations);
            foodFacts.setHealthscore(90);

            firebaseService.setFoodFacts("", foodFacts);

            return image.getId();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Long(-1);
    }

    @RequestMapping(value="/{id}", method= RequestMethod.GET, produces="application/json")
    public ResponseEntity<byte[]> getImage(@PathVariable("id") Long id) {
        byte [] image = cleanFoodRepository.findOne(id).getImageData();
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image);
    }
}
