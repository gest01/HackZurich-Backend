package ch.raiffeisen.hackzurich.service.google;

import com.google.api.services.vision.v1.model.EntityAnnotation;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by simon on 16.09.2017.
 */
public class GoogleVisionClientTest {

    @Test
    public void labelImage() throws Exception {
        GoogleVisionClient client = new GoogleVisionClient();

        Path imagePath = Paths.get("C:/temp/hackzurich/tomate2.jpg");
        byte[] data = Files.readAllBytes(imagePath);

        List<EntityAnnotation> entityAnnotations = client.labelImage(data);
        for (EntityAnnotation entityAnnotation : entityAnnotations) {
            System.out.println(entityAnnotation);
            System.out.println(entityAnnotation.getProperties());
        }


    }

}