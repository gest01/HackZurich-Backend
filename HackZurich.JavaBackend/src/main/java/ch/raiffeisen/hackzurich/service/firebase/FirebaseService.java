package ch.raiffeisen.hackzurich.service.firebase;

import ch.raiffeisen.hackzurich.dto.Entry;
import ch.raiffeisen.hackzurich.dto.FoodFacts;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by fabian on 16.09.2017.
 */
@Component
public class FirebaseService {

//    public void createEntry(FoodFacts entry) {
//        RestTemplate restTemplate = new RestTemplate();
//        restTemplate.postForEntity("https://hackzurich2017.firebaseio.com/entries.json", entry, FoodFacts.class);
//    }

    public Map<String, Entry> getAllEntries() {
        RestTemplate restTemplate = new RestTemplate();
        String json = restTemplate.getForObject("https://hackzurich2017.firebaseio.com/entries.json", String.class);

        Map<String, Entry> entries;

        if("null".equals(json)) {
            entries = new HashMap<>();
        } else {
            Gson gson = new Gson();
            entries = gson.fromJson(json, new TypeToken<Map<String, Entry>>(){}.getType());
        }

        return entries;
    }

    public void setFoodFacts(String entryId, FoodFacts foodFacts) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.put("https://hackzurich2017.firebaseio.com/entries/" + entryId + "/foodFacts.json", foodFacts, FoodFacts.class);
    }
}
