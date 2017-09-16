package ch.raiffeisen.hackzurich.controller;

import ch.raiffeisen.hackzurich.repositories.CleanFoodImageRepository;
import ch.raiffeisen.hackzurich.service.CleanfoodService;
import ch.raiffeisen.hackzurich.service.firebase.FirebaseService;
import ch.raiffeisen.hackzurich.service.google.GoogleVisionClient;
import com.google.api.services.vision.v1.model.EntityAnnotation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.io.*;
import java.util.List;

@RestController
@RequestMapping("/api/cleanfood/image")
public class CleanfoodController {
    private final Logger logger = LoggerFactory.getLogger(CleanfoodController.class);

    @Resource
    private CleanFoodImageRepository cleanFoodRepository;

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

    @RequestMapping(value="/{entryId}/{imageId}", method= RequestMethod.GET, produces="text/plain")
    public ResponseEntity<String> processImage(@PathVariable(value="entryId") String entryId,
                                          @PathVariable(value="imageId") Long imageId) throws Exception {

        cleanfoodService.analyze(imageId, entryId);

        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body("Ok");
    }
}
