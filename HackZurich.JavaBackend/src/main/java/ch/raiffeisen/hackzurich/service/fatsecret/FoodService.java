package ch.raiffeisen.hackzurich.service.fatsecret;

import com.fatsecret.platform.model.CompactFood;
import com.fatsecret.platform.model.Food;
import com.fatsecret.platform.services.FatsecretService;
import com.fatsecret.platform.services.Response;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FoodService {

    private static final String API_KEY = "b3422a1cd0824284942a6c2dde064c97";
    private static final String API_REST_SECRET = "26e7d65017924a72987cf7c0a4b08af8";
    private static final int MAX_SEARCH_DETAIL = 3;

    private FatsecretService fatsecretService;


    public FoodService() {
       fatsecretService= new FatsecretService(API_KEY, API_REST_SECRET);
    }

    public List<CompactFood> getFoodFacts(String food) {
        String query = "pasta"; //Your query string
        Response<CompactFood> response = fatsecretService.searchFoods(query);
        List<CompactFood> result = new ArrayList<>();
        int counter = 0;
        for (CompactFood compactFood : response.getResults()) {
            result.add(compactFood);
            if(counter>MAX_SEARCH_DETAIL) {
                break;
            }
        }
        return result;
    }

    public Food getFoodDetails(CompactFood compactFood) {
        return fatsecretService.getFood(compactFood.getId());
    }

    public List<Food> getFoodDetails(List<CompactFood> compactFoods) {
        List<Food> result = new ArrayList<>();
        for (CompactFood compactFood : compactFoods) {
            result.add(fatsecretService.getFood(compactFood.getId()));
        }
        return result;
    }

}
