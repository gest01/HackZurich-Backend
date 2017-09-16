package ch.raiffeisen.hackzurich.controller;

import ch.raiffeisen.hackzurich.service.fatsecret.FoodService;
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

    @RequestMapping(value="/searchFoods/{food}", method= RequestMethod.GET, produces="application/json")
    public List<Food> getFatSecret(@PathVariable("food") String food) {
        List<CompactFood> foodFacts = foodService.getFoodFacts(food);
        return foodService.getFoodDetails(foodFacts);
    }
}
