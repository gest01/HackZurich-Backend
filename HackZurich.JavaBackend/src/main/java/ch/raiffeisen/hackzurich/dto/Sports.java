package ch.raiffeisen.hackzurich.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by simon on 16.09.2017.
 */
public class Sports {
    private BigDecimal joggenInKM;
    private BigDecimal joggenInMinuten;
    private BigDecimal schwimmenInKM;
    private BigDecimal schwimmenInMinuten;
    private BigDecimal radfahrenInKM;
    private BigDecimal radfahrenInMinuten;
    private BigDecimal angelnInMinuten;
    private BigDecimal angelnAnzahlFische;
    private List<String> text;

    public Sports(Long cal) {
        BigDecimal calories = new BigDecimal(cal);
        calories =  calories.multiply(new BigDecimal(3));
        if(calories.compareTo(new BigDecimal(100))<0) {
            calories = new BigDecimal(300);
        }
        joggenInKM = divide(calories, new BigDecimal(150), 2);
        joggenInMinuten = divide(calories, new BigDecimal(25), 0);
        schwimmenInKM = divide(calories, new BigDecimal(120), 2);
        schwimmenInMinuten = divide(calories, new BigDecimal(20), 0);
        radfahrenInKM  = divide(calories, new BigDecimal(15), 2);
        radfahrenInMinuten  = divide(calories, new BigDecimal(6), 0);
        angelnInMinuten = divide(calories, new BigDecimal(1), 0);
        angelnAnzahlFische = new BigDecimal(5);
        text = new ArrayList<>();
        text.add("Jogging in kilometers: "+joggenInKM);
        text.add("Jogging in minutes: "+joggenInMinuten);
        text.add("Swimming in kilometers: "+schwimmenInKM);
        text.add("Swimming in minutes: "+schwimmenInMinuten);
        text.add("Cycling in kilometers: "+radfahrenInKM);
        text.add("Cycling in minutes: "+radfahrenInMinuten);
        text.add("Fishing in minutes: "+angelnAnzahlFische);
        text.add("Fishes count: "+angelnAnzahlFische);
    }


    private BigDecimal divide(BigDecimal number, BigDecimal divider, int scale) {
        return number.divide(divider, scale, RoundingMode.HALF_UP);

    }

    public BigDecimal getJoggenInKM() {
        return joggenInKM;
    }

    public void setJoggenInKM(BigDecimal joggenInKM) {
        this.joggenInKM = joggenInKM;
    }

    public BigDecimal getJoggenInMinuten() {
        return joggenInMinuten;
    }

    public void setJoggenInMinuten(BigDecimal joggenInMinuten) {
        this.joggenInMinuten = joggenInMinuten;
    }

    public BigDecimal getSchwimmenInKM() {
        return schwimmenInKM;
    }

    public void setSchwimmenInKM(BigDecimal schwimmenInKM) {
        this.schwimmenInKM = schwimmenInKM;
    }

    public BigDecimal getSchwimmenInMinuten() {
        return schwimmenInMinuten;
    }

    public void setSchwimmenInMinuten(BigDecimal schwimmenInMinuten) {
        this.schwimmenInMinuten = schwimmenInMinuten;
    }

    public BigDecimal getRadfahrenInKM() {
        return radfahrenInKM;
    }

    public void setRadfahrenInKM(BigDecimal radfahrenInKM) {
        this.radfahrenInKM = radfahrenInKM;
    }

    public BigDecimal getRadfahrenInMinuten() {
        return radfahrenInMinuten;
    }

    public void setRadfahrenInMinuten(BigDecimal radfahrenInMinuten) {
        this.radfahrenInMinuten = radfahrenInMinuten;
    }

    public BigDecimal getAngelnInMinuten() {
        return angelnInMinuten;
    }

    public void setAngelnInMinuten(BigDecimal angelnInMinuten) {
        this.angelnInMinuten = angelnInMinuten;
    }

    public BigDecimal getAngelnAnzahlFische() {
        return angelnAnzahlFische;
    }

    public void setAngelnAnzahlFische(BigDecimal angelnAnzahlFische) {
        this.angelnAnzahlFische = angelnAnzahlFische;
    }

    public List<String> getText() {
        return text;
    }

    public void setText(List<String> text) {
        this.text = text;
    }
}
