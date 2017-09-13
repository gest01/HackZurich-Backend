package ch.raiffeisen.hackzurich.repositories;

import ch.raiffeisen.hackzurich.domain.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by simon on 12.09.2017.
 */
@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {


}
