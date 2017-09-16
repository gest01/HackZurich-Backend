package ch.raiffeisen.hackzurich.controller;

import ch.raiffeisen.hackzurich.service.fatsecret.FoodService;
import ch.raiffeisen.hackzurich.service.fatsecret.HealthCalculator;
import ch.raiffeisen.hackzurich.service.fatsecret.HealthInformation;
import com.fatsecret.platform.model.CompactFood;
import com.fatsecret.platform.model.Food;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by simon on 16.09.2017.
 */
@RestController
@RequestMapping("/api/fatsecret")
public class FatSecretController {

    @Resource
    private FoodService foodService;

    @Resource
    private HealthCalculator healthCalculator;

    @RequestMapping(value="/searchFoods", method= RequestMethod.GET, produces="application/json")
    public List<Food> getFatSecret(@RequestParam("food") String food) {
        List<CompactFood> foodFacts = foodService.getFoodFacts(food);
        List<Food> foodDetails = foodService.getFoodDetails(foodFacts);
        HealthInformation healthInformation = healthCalculator.calculateHealth(foodDetails);
        System.out.println("HEALTH VALUE: "+healthInformation.getHealthScore());
        return foodDetails;
    }

    @RequestMapping(value="/searchCompactFoods/{food}", method= RequestMethod.GET, produces="application/json")
    public List<CompactFood> getFatSecretCompact(@PathVariable("food") String food) {
         return foodService.getFoodFacts(food);
    }
}
