package ch.raiffeisen.hackzurich.dto;

import com.fatsecret.platform.model.Serving;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by simon on 16.09.2017.
 */
public class NutritionFacts {

    private List<String> text = new ArrayList<>();

    public NutritionFacts(Serving serving) {
        addText("Total Fat", serving.getFat());
        addText("  SaturatedFat", serving.getSaturatedFat());
        addText("  TransFat", serving.getTransFat());
        addText("  MonounsaturatedFat", serving.getMonounsaturatedFat());
        addText("  PolyunsaturatedFat", serving.getPolyunsaturatedFat());
        addText("Calories", serving.getCalories());
        addText("Sugar", serving.getSugar());
        addText("Calcium", serving.getCalcium());
        addText("Carhobydrate", serving.getCarbohydrate());
        addText("Cholesterol", serving.getCholesterol());
        addText("Fiber", serving.getFiber());
        addText("Iron",serving.getIron() );
        addText("Potassium", serving.getPotassium());
        addText("Protein", serving.getProtein());
        addText("Sodium",  serving.getSodium());
        addText("VitaminA",serving.getVitaminA() );
        addText("VitaminC",serving.getVitaminC() );

    }

    private void addText(String key, BigDecimal value) {
        if(value != null) {
            text.add(key+": "+value);
        }

    }

    public List<String> getText() {
        return text;
    }

    public void setText(List<String> text) {
        this.text = text;
    }
}
