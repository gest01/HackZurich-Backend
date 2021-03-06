package ch.raiffeisen.hackzurich.controller;

import ch.raiffeisen.hackzurich.domain.CleanFoodImage;
import ch.raiffeisen.hackzurich.domain.ImageFood;
import ch.raiffeisen.hackzurich.dto.ImageDetail;
import ch.raiffeisen.hackzurich.repositories.CleanFoodImageRepository;
import ch.raiffeisen.hackzurich.repositories.ImageFoodRepository;
import ch.raiffeisen.hackzurich.service.CleanfoodService;
import ch.raiffeisen.hackzurich.service.firebase.FirebaseService;
import ch.raiffeisen.hackzurich.service.google.GoogleVisionClient;
import ch.raiffeisen.hackzurich.utils.ThumbnailGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/cleanfood/image")
public class CleanfoodController {
    private final Logger logger = LoggerFactory.getLogger(CleanfoodController.class);

    @Resource
    private CleanFoodImageRepository cleanFoodRepository;

    @Resource
    private ImageFoodRepository imageFoodRepository;

    @Resource
    private CleanfoodService cleanfoodService;

    @Resource
    private GoogleVisionClient googleVisionClient;

    @Resource
    private FirebaseService firebaseService;


    @PostMapping("/upload")
    public Long handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        try {
            byte[] thumb = ThumbnailGenerator.createThumb(file.getBytes(), 300, 300);
            return cleanfoodService.saveImage(file.getBytes(), thumb);
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

    @RequestMapping(value="/thumb/{id}", method= RequestMethod.GET, produces="application/json")
    public ResponseEntity<byte[]> getThumb(@PathVariable("id") Long id) {
        byte [] image = cleanFoodRepository.findOne(id).getThumbnailData();
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image);
    }


    @RequestMapping(value="/processImage/{entryId}/{imageId}", method= RequestMethod.POST, produces="text/plain")
    public ResponseEntity<String> processImage(@PathVariable(value="entryId") String entryId,
                                          @PathVariable(value="imageId") Long imageId) throws Exception {

        cleanfoodService.analyze(imageId, entryId);

        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body("Ok");
    }

    @RequestMapping(value="/details/{id}", method= RequestMethod.GET, produces="application/json")
    public List<ImageDetail> getImageFoodDetails(@PathVariable("id") Long id) {
        CleanFoodImage image = cleanFoodRepository.findOne(id);
        Iterable<ImageFood> all = imageFoodRepository.findByCleanFoodImage(image);

        return getImagesDetails(image, all);
    }

    private List<ImageDetail> getImagesDetails(CleanFoodImage image, Iterable<ImageFood> foods) {
        List<ImageDetail> details = new ArrayList<>();
        for (ImageFood food : foods) {
            details.add(ImageDetail.from(image, food));
        }
        return details;


    }
}
