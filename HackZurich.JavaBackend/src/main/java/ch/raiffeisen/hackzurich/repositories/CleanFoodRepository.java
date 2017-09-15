package ch.raiffeisen.hackzurich.repositories;

import ch.raiffeisen.hackzurich.domain.CleanFoodImage;
import ch.raiffeisen.hackzurich.domain.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by simon on 12.09.2017.
 */
@Repository
public interface CleanFoodRepository extends CrudRepository<CleanFoodImage, Long> {


}
