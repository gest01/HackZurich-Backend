package ch.raiffeisen.hackzurich.controller;

import ch.raiffeisen.hackzurich.domain.CleanFoodImage;
import ch.raiffeisen.hackzurich.repositories.CleanFoodRepository;
import ch.raiffeisen.hackzurich.repositories.PersonRepository;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.websocket.server.PathParam;
import java.io.*;

@RestController
@RequestMapping("/api/cleanfood/image")
public class CleanfoodController {


    @Resource
    private CleanFoodRepository cleanFoodRepository;

    @PostMapping("/uploadAndAnalyze")
    public Long handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        try {
            CleanFoodImage image = new CleanFoodImage();
            image.setImageData(file.getBytes());
            cleanFoodRepository.save(image);
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
