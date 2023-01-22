/*
ASSIGNMENT NUMBER : Home Work 4
NAME : Deep Rajesh Furiya
       Surabhi Jagadeesh
*/


package com.example.group10hw03;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;

public class Drink implements Serializable, Comparator<Drink> {
    public Double drinkSize;
    public Integer alcoholPercent;
    public Date timestamp;

    public Drink(Double drinkoz, Integer drinkalcaholpercentage, Date timestamp) {
        this.drinkSize = drinkoz;
        this.alcoholPercent = drinkalcaholpercentage;
        this.timestamp = timestamp;
    }
    public Drink() {
    }

    public Double getDrinkSize() {
        return drinkSize;
    }

    public void setDrinkSize(Double drinkSize) {
        this.drinkSize = drinkSize;
    }

    public Integer getAlcoholPercent() {
        return alcoholPercent;
    }

    public void setAlcoholPercent(Integer alcoholPercent) {
        this.alcoholPercent = alcoholPercent;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public int compare(Drink d1, Drink d2) {
        return d1.alcoholPercent.compareTo(d2.alcoholPercent);
    }



}
