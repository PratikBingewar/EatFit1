package com.eatfit.presenter.currentDietList;

import java.util.HashMap;
import java.util.Map;

public class CurrentDietItemsListHolder {
    Map<String, Double> FoodNameAndItsCalories;

    String[] FoodNames = {"milk","tea","coffee","oatmeal",
                            "banana","apple","pohe","upma",
                            "potato","green vegetable","muesli","tomato",
                            "cucumber","egg","paratha","dal",
                            "rice","chicken","mutton","fish",
                            "chinese","bread","idli",
                            "dosa","cornflakes","roti","chapati","biryani"
    };

    Double[] CalorificValues = {117.0,52.0,122.0,208.0,
                                70.0,28.0,216.0,177.0,
                                177.0,100.0,289.0,12.0,
                                16.0,84.0,150.0,100.0,
                                111.0,287.0,274.0,116.0,
                                236.0,39.0,102.0,
                                277.0,288.0,400.0,158.0,251.0
    };

    public CurrentDietItemsListHolder() {

        FoodNameAndItsCalories = new HashMap<String,Double>();
        for (int i=0 ; i<FoodNames.length ; i++ ){
            FoodNameAndItsCalories.put(FoodNames[i],CalorificValues[i]);
        }
    }

    public double calculateCurrentConsumption(String[] FoodList) {
        double currentIntake=0;
        for(int i=0; i<FoodList.length ;i++){
            currentIntake = currentIntake + FoodNameAndItsCalories.get(FoodList[i]);
        }
        return currentIntake;
    }


}
