package ch.raiffeisen.hackzurich.service.google;

import com.google.api.services.vision.v1.model.EntityAnnotation;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by simon on 16.09.2017.
 */
public class GoogleVisionLanguageTest {

    @Test
    public void categorizeImage() throws Exception {
        GoogleLanguageClient client = new GoogleLanguageClient();

//        client.determineCategory();

        client.callSpoonacular();
//        client.callAylien();
    }

}