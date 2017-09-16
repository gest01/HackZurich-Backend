package ch.raiffeisen.hackzurich.service.firebase;

import ch.raiffeisen.hackzurich.dto.Entry;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FirebaseServiceTests {

	@Autowired
	private FirebaseService firebaseService;

	@Test
	public void createEntry() {
//		FoodFacts entry = new FoodFacts();
//		entry.setImageUrl("/blub.jpg");
//		entry.setHealthscore(27);
//
//		firebaseService.createEntry(entry);
	}

//	@Test
//	public void getAllEntries() throws Exception {
//		Map<String, Entry> entries = firebaseService.getAllEntries();
//
//		for (Map.Entry<String, Entry> entry : entries.entrySet()) {
//			System.out.println("Entry : " + entry.getKey() + " by User : " + entry.getValue().getUser().getDisplayName());
//		}
//	}

//	@Test
//	public void setFoodFactsForAllEntries() throws Exception {
//		GoogleVisionClient client = new GoogleVisionClient();
//
//		Path imagePath = Paths.get("C:\\Users\\fabian\\Desktop\\foodTest.jpg");
//		byte[] data = Files.readAllBytes(imagePath);
//		List<EntityAnnotation> entityAnnotations = client.labelImage(data);
//
//		Map<String, Entry> entries = firebaseService.getAllEntries();
//		FoodFacts foodFacts;
//
//		for (HashMap.Entry<String, Entry> entry : entries.entrySet()) {
//			foodFacts = new FoodFacts();
//			foodFacts.setGoogle(entityAnnotations);
//			foodFacts.setHealthscore(27);
//
//			firebaseService.setFoodFacts(entry.getKey(), foodFacts);
//		}
//	}

//	@Test
//	public void setFoodFacts() throws Exception {
//		GoogleVisionClient client = new GoogleVisionClient();
//
//		Path imagePath = Paths.get("C:\\Users\\fabian\\Desktop\\foodTest.jpg");
//		byte[] data = Files.readAllBytes(imagePath);
//
//		List<EntityAnnotation> entityAnnotations = client.labelImage(data);
//
//		FoodFacts foodFacts = new FoodFacts();
//		foodFacts.setGoogle(entityAnnotations);
//		foodFacts.setHealthscore(27);
//
//		firebaseService.setFoodFacts("-Ku8iEBPKVXLQ-L2xtY6", foodFacts);
//	}

}
