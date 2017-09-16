package ch.raiffeisen.hackzurich.service.firebase;

import ch.raiffeisen.hackzurich.dto.Entry;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

/**
 * Created by fabian on 16.09.2017.
 */
@Component
public class FirebaseService {

    public void createEntry(Entry entry) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForEntity("https://hackzurich2017.firebaseio.com/entries.json", entry, Entry.class);
    }
}
