package ch.raiffeisen.hackzurich.controller;

import ch.raiffeisen.hackzurich.domain.CleanFoodImage;
import ch.raiffeisen.hackzurich.dto.Entry;
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
    private CleanfoodService cleanfoodService;

    @Resource
    private GoogleVisionClient googleVisionClient;

    @Resource
    private FirebaseService firebaseService;


    @PostMapping("/uploadAndAnalyze")
    public Long handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        try {
            Long imageID = cleanfoodService.saveImage(file.getBytes());
            List<EntityAnnotation> entityAnnotations = cleanfoodService.getGoogleLabelData(file.getBytes());
            cleanfoodService.createFirebaseEntry(entityAnnotations);
            return imageID;
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
