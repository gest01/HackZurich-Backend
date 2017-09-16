package ch.raiffeisen.hackzurich.controller;

import ch.raiffeisen.hackzurich.service.fatsecret.FoodService;
import ch.raiffeisen.hackzurich.service.fatsecret.HealthCalculator;
import com.fatsecret.platform.model.CompactFood;
import com.fatsecret.platform.model.Food;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(value="/searchFoods/{food}", method= RequestMethod.GET, produces="application/json")
    public List<Food> getFatSecret(@PathVariable("food") String food) {
        List<CompactFood> foodFacts = foodService.getFoodFacts(food);
        List<Food> foodDetails = foodService.getFoodDetails(foodFacts);
        Long aLong = healthCalculator.calculateHealth(foodDetails);
        System.out.println("HEALTH VALUE: "+aLong);
        return foodDetails;
    }

    @RequestMapping(value="/searchCompactFoods/{food}", method= RequestMethod.GET, produces="application/json")
    public List<CompactFood> getFatSecretCompact(@PathVariable("food") String food) {
         return foodService.getFoodFacts(food);
    }
}
