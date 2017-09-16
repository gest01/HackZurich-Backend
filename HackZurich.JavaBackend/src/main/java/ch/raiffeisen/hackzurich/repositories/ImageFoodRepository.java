package ch.raiffeisen.hackzurich.repositories;

import ch.raiffeisen.hackzurich.domain.CleanFoodImage;
import ch.raiffeisen.hackzurich.domain.ImageFood;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by simon on 12.09.2017.
 */
@Repository
public interface ImageFoodRepository extends CrudRepository<ImageFood, Long> {


}
