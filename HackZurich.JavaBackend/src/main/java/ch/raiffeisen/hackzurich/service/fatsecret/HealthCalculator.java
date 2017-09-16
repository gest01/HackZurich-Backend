package ch.raiffeisen.hackzurich.service.fatsecret;

import com.fatsecret.platform.model.Food;
import com.fatsecret.platform.model.Serving;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Component
public class HealthCalculator {

    private static BigDecimal MAGIC_FACTOR = new BigDecimal("0.8");



    public Long calculateHealth(List<Food> foods) {
        BigDecimal fatSum = BigDecimal.ZERO;
        BigDecimal sugarSum = BigDecimal.ZERO;
        BigDecimal caloriesSum = BigDecimal.ZERO;



        int counter = 0;
        for (Food food : foods) {
            //Hier nur den ersten Treffer verwenden
            Serving serving = food.getServings().get(0);
            counter++;
            fatSum = fatSum.add(serving.getFat());
            sugarSum = sugarSum.add(serving.getSugar());
            caloriesSum = caloriesSum.add(serving.getCalories());
            /*
            for (Serving serving : food.getServings()) {
                counter++;
                fatSum = fatSum.add(serving.getFat());
                sugarSum = sugarSum.add(serving.getSugar());
                caloriesSum = caloriesSum.add(serving.getCalories());
            }
            */
        }

        fatSum = divide(fatSum, new BigDecimal((counter)));
        sugarSum = divide(sugarSum, new BigDecimal((counter)));
        caloriesSum = divide(caloriesSum, new BigDecimal((counter)));
        System.out.println(fatSum);
        System.out.println(sugarSum);
        System.out.println(caloriesSum);
        caloriesSum = getCaloriesIndicator(caloriesSum);
        System.out.println(caloriesSum);

        BigDecimal overallSum = fatSum.add(sugarSum).add(caloriesSum);
        BigDecimal overall = divide(overallSum, new BigDecimal(3));
        BigDecimal overallWithFactor = overall.multiply(MAGIC_FACTOR);
        BigDecimal healthScore = new BigDecimal(100).subtract(overallWithFactor);
        return healthScore.intValue() < 0 ? BigDecimal.ZERO.longValue() : healthScore.longValue();
    }


    private BigDecimal divide(BigDecimal number, BigDecimal divider) {
        return number.divide(divider, 0, RoundingMode.HALF_UP);

    }


    private BigDecimal getCaloriesIndicator(BigDecimal caloriesSum) {
        int calories = caloriesSum.intValue();

        if(calories<50) {
            return new BigDecimal(10);
        } else if(calories<100) {
            return new BigDecimal(20);
        } else if(calories<200) {
            return new BigDecimal(35);
        } else if(calories<300) {
            return new BigDecimal(50);
        }  else if(calories<400) {
            return new BigDecimal(60);
        } else if(calories<500) {
            return new BigDecimal(70);
        }  else {
            return new BigDecimal(100);
        }
    }
}
