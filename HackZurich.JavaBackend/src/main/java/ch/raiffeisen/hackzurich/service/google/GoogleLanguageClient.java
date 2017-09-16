package ch.raiffeisen.hackzurich.service.google;

import com.aylien.textapi.TextAPIClient;
import com.aylien.textapi.TextAPIException;
import com.aylien.textapi.parameters.EntitiesParams;
import com.aylien.textapi.parameters.SentimentParams;
import com.aylien.textapi.responses.Entities;
import com.aylien.textapi.responses.Entity;
import com.aylien.textapi.responses.Sentiment;
import com.google.api.services.vision.v1.Vision;
import com.rapidapi.rapidconnect.Argument;
import com.rapidapi.rapidconnect.RapidApiConnect;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class GoogleLanguageClient {
    /**
     * Be sure to specify the name of your application. If the application name is {@code null} or
     * blank, the application will log a warning. Suggested format is "MyCompany-ProductName/1.0".
     */
    private static final String APPLICATION_NAME = "Google-VisionLabelSample/1.0";

    private static final int MAX_LABELS = 10;

    private static Vision vision;

    public GoogleLanguageClient() {
        System.setProperty("GOOGLE_APPLICATION_CREDENTIALS", "C:/Develop/WS/Hackzurich/HackZurich-Backend/HackZurich.JavaBackend/hackzurich-api.json");
    }

//    public String determineCategory() {
//        try {
//            LanguageServiceClient language = LanguageServiceClient.create();
//
//            // The text to analyze
//            String text = "Schinken";
//            Document doc = Document.newBuilder().setContent(text).setType(Document.Type.PLAIN_TEXT).build();
//
//            // Detects the sentiment of the text
//            AnalyzeEntitiesResponse response = language.analyzeEntities(doc, EncodingType.NONE);
//            for (Entity entity : response.getEntitiesList()) {
//                System.out.printf(entity.getType().name());
//            }
//
////            System.out.printf("Sentiment: %s, %s%n",
//        } catch (IOException e) {
//            System.out.println("Error Language Clinet: " + e.getMessage());
//        }
//
//        return "";
//    }

    public void callSpoonacular() throws IOException {
        RapidApiConnect connect = new RapidApiConnect("foodchallange_59bd44d8e4b0b0cacf7c7ea4", "65f41a92-3374-4f14-b653-0f16d2a26e71");

        Map<String, String> body = new HashMap<String, String>();

//        body.put("num1", new Argument("data", "11"));
//        body.put("num2", new Argument("data","2"));

        body.put("test", "test");

        Map<String, Object> response = connect.call("Spoonacular", "", body);

        System.out.println("hallo");
    }

    public void callAylien() throws TextAPIException {
        TextAPIClient client = new TextAPIClient("158fbf57", "8886f70322c3d6f609fe99c3d623376b");

        EntitiesParams.Builder builder = EntitiesParams.newBuilder();
        String text = "An apple a day keeps the doctor away";
        builder.setText(text);
        Entities entities = client.entities(builder.build());
        for (Entity entity: entities.getEntities()) {
            System.out.print(entity.getType() + ": ");
            for (String sf: entity.getSurfaceForms()) {
                System.out.print("\"" + sf + "\" ");
            }
            System.out.println();
        }
    }
}
