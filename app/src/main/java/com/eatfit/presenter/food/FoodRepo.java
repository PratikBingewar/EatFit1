package com.eatfit.presenter.food;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
/**
 * Created by Tan on 3/14/2016.
 */


public class FoodRepo {
    private DBHelper dbHelper;

    public FoodRepo(Context context) {
        dbHelper = new DBHelper(context);
    }

    public int insert(Food food) {

        //Open connection to write data
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Food.KEY_name,food.KEY_name);
        values.put(Food.KEY_calories, food.KEY_calories);

        // Inserting Row
        long Food_Id = db.insert(Food.TABLE, null, values);

        return (int) Food_Id;
    }
    public Cursor  getFoodList() {
        //Open connection to read only
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT * "+" FROM " + Food.TABLE;


        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor == null) {
            return null;
        } else if (!cursor.moveToFirst()) {
            cursor.close();
            return null;
        }
        return cursor;
    }


    public Cursor  getFoodByKeyword(String search) {
        //Open connection to read only
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  *" +
                " FROM " + Food.TABLE +
                " WHERE " +  Food.KEY_name + "  LIKE  '%" +search + "%' "
                ;


        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor == null) {
            return null;
        } else if (!cursor.moveToFirst()) {
            cursor.close();
            return null;
        }
        return cursor;

    }

}