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



    public HealthInformation calculateHealth(List<Food> foods) {
        BigDecimal fatSum = BigDecimal.ZERO;
        BigDecimal sugarSum = BigDecimal.ZERO;
        BigDecimal caloriesSum = BigDecimal.ZERO;



        int counter = 0;
        for (Food food : foods) {
            //Hier nur den ersten Treffer verwenden
            Serving serving = food.getServings().get(0);
            counter++;
            fatSum = fatSum.add(serving.getFat()!=null ? serving.getFat() : BigDecimal.ONE);
            sugarSum = sugarSum.add(serving.getSugar()!=null ? serving.getSugar() : BigDecimal.ONE);
            caloriesSum = caloriesSum.add(serving.getCalories()!=null ? serving.getCalories() : BigDecimal.ONE);
            caloriesSum = calculateTo100G(caloriesSum, serving.getMetricServingAmount());
            sugarSum = calculateTo100G(sugarSum, serving.getMetricServingAmount());
            fatSum = calculateTo100G(fatSum, serving.getMetricServingAmount());
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
        caloriesSum = getCaloriesIndicator(caloriesSum);



        System.out.println("FAT: "+fatSum);
        System.out.println("SUGAR:" +sugarSum);
        System.out.println("CALROIES"+caloriesSum);

        System.out.println(caloriesSum);

        BigDecimal overallSum = fatSum.add(sugarSum).add(caloriesSum);
        BigDecimal overall = divide(overallSum, new BigDecimal(3));
        BigDecimal overallWithFactor = overall.multiply(MAGIC_FACTOR);
        BigDecimal healthScore = new BigDecimal(100).subtract(overallWithFactor);
        healthScore =  healthScore.intValue() < 0 ? BigDecimal.ZERO : healthScore;
        return new HealthInformation(sugarSum.longValue(), fatSum.longValue(), caloriesSum.longValue(), healthScore.longValue());
    }


    private BigDecimal divide(BigDecimal number, BigDecimal divider) {
        return number.divide(divider, 0, RoundingMode.HALF_UP);

    }

    private BigDecimal calculateTo100G(BigDecimal calories, BigDecimal metricServingUnit) {
        BigDecimal factor = divide(new BigDecimal(100), metricServingUnit);
        return calories.multiply(factor);
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
