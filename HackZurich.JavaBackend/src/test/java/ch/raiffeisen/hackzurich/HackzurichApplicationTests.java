package ch.raiffeisen.hackzurich;

import ch.raiffeisen.hackzurich.dto.Entry;
import ch.raiffeisen.hackzurich.service.firebase.FirebaseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HackzurichApplicationTests {

	@Autowired
	private FirebaseService firebaseService;

	@Test
	public void contextLoads() {
	}

	@Test
	public void createEntry() {
		Entry entry = new Entry();
		entry.setImageUrl("/blub.jpg");
		entry.setHealthscore(27);

		firebaseService.createEntry(entry);
	}

}
