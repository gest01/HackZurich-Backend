package ch.raiffeisen.hackzurich.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@RestController
public class HelloController {

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot by stef!";
    }


    @RequestMapping(path = "/userhome", method= RequestMethod.GET)
    public String userhome() {
        return System.getProperty("user.dir");
    }

    @RequestMapping(path = "/files", method= RequestMethod.GET)
    public List<String> files() {
        List<String> result = new ArrayList<>();
        File file  = new File(System.getProperty("user.dir"));
        File[] files = file.listFiles();
        for (File file1 : files) {
            result.add(file1.getAbsolutePath());
        }
        return result;
    }


    @RequestMapping(path = "/application", method= RequestMethod.GET)
    public String applicationConfig() {
        try {
            return new Scanner(new File(System.getProperty("user.dir") + "/application.config")).useDelimiter("\\Z").next();
        }catch(Exception e) {
            return e.getMessage();
        }
    }
}