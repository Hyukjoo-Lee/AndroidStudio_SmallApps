package com.example.caloriecounter;

public class Burger {
    static final int BEEF = 120;
    static final int LAMB = 190;
    static final int OSTRICH = 170;
    static final int ASIAGO = 100;
    static final int CREME_FRAICHE = 130;
    static final int PROSCIUTTO = 125;

    private int pattyCal;
    private int cheeseCal;
    private int prosciuttoCal;
    private int sauceCal;


    public Burger() {
        pattyCal = BEEF;
        cheeseCal = ASIAGO;
        prosciuttoCal = 0;
        sauceCal = 0;
    }

    public void setPattyCalories(int calories) {
        pattyCal = calories;
    }

    public void setCheeseCalories(int calories) {
        cheeseCal = calories;
    }

    public void setProsciuttoCalories(int calories) {
        prosciuttoCal = calories;
    }

    public void clearProsciuttoCalories() {
        prosciuttoCal = 0;
    }

    public void setSauceCalories(int calories) {
        sauceCal = calories;
    }

    public int getTotalCalories() {
        return pattyCal + cheeseCal + prosciuttoCal + sauceCal;
    }

}