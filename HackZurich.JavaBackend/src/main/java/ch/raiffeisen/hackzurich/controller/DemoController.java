package ch.raiffeisen.hackzurich.controller;

import ch.raiffeisen.hackzurich.domain.Person;
import ch.raiffeisen.hackzurich.repository.PersonRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by simon on 20.06.2017.
 */
@RestController
@RequestMapping("/api/personen")
public class DemoController {

    @Resource
    private PersonRepository personRepository;


    @RequestMapping(path = "/", method= RequestMethod.GET)
    public Iterable<Person> listPersonen() {
        return personRepository.listPerson();
    }


    @RequestMapping("/userhome")
    public String userhome() {
        return System.getProperty("user.dir");
    }

    @RequestMapping("/files")
    public List<String> files() {
        List<String> result = new ArrayList<>();
        File file  = new File(System.getProperty("user.dir"));
        File[] files = file.listFiles();
        for (File file1 : files) {
            result.add(file1.getAbsolutePath());
        }
        return result;
    }

}
