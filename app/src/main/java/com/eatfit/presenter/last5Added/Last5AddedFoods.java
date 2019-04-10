package com.eatfit.presenter.last5Added;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Last5AddedFoods {
    Map<Integer,String> Last5AddedFoods;

    public void createFoodNames(){
        Last5AddedFoods = new HashMap(5);
    }

    public Map getAllFoodNamesInDescendingOrder(){
        Map<Integer,String> Last5AddedFoodsReverse = new HashMap<Integer, String>(Last5AddedFoods.size());

        ArrayList keyList = new ArrayList(Last5AddedFoods.keySet());
        for (int j=0,i = keyList.size() - 1; i >= 0; i--,j++) {

            String value = Last5AddedFoods.get(i);
            Last5AddedFoodsReverse.put(j,value);

        }
        return Last5AddedFoodsReverse;
    }

    public  void addFoodName(String foodName){

        int size = Last5AddedFoods.size();
        Last5AddedFoods.put(++size,foodName);
    }
}
