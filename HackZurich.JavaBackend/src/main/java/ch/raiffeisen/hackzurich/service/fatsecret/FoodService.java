package ch.raiffeisen.hackzurich.service.fatsecret;

import com.fatsecret.platform.model.CompactFood;
import com.fatsecret.platform.model.CompactRecipe;
import com.fatsecret.platform.model.Food;
import com.fatsecret.platform.model.Recipe;
import com.fatsecret.platform.services.FatsecretService;
import com.fatsecret.platform.services.Response;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FoodService {

    private static final String API_KEY = "b3422a1cd0824284942a6c2dde064c97";
    private static final String API_REST_SECRET = "26e7d65017924a72987cf7c0a4b08af8";
    private static final int MAX_SEARCH_DETAIL = 2;
    private static final String FOOD_TYPE = "Generic";
    private FatsecretService fatsecretService;


    public FoodService() {
       fatsecretService= new FatsecretService(API_KEY, API_REST_SECRET);
    }

    public List<CompactFood> getFoodFacts(String food) {
        String query = food; //Your query string
        Response<CompactFood> response = fatsecretService.searchFoods(query);
        List<CompactFood> result = new ArrayList<>();
        int counter = 0;
        for (CompactFood compactFood : response.getResults()) {
            if(FOOD_TYPE.equals(compactFood.getType())) {
                counter++;
                result.add(compactFood);
                if (counter > MAX_SEARCH_DETAIL) {
                    break;
                }
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


    public List<CompactRecipe> getRecipes(String food) {
        Response<CompactRecipe> compactRecipeResponse = fatsecretService.searchRecipes(food);
        int counter = 0;
        List<CompactRecipe> result = new ArrayList<>();
        for (CompactRecipe compactFood : compactRecipeResponse.getResults()) {
            counter++;
            result.add(compactFood);
            if (counter > MAX_SEARCH_DETAIL) {
                break;
            }
        }
        return result;
    }

    public List<Recipe> getRecipeDetails(List<CompactRecipe> compactRecipes) {
        List<Recipe> result = new ArrayList<>();
        for (CompactRecipe recipe : compactRecipes) {
            result.add(fatsecretService.getRecipe(recipe.getId()));
        }
        return result;
    }

}
