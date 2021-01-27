package com.ptkzonelabs.rupeefy.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String CATEGORY_TABLE = "CATEGORY_TABLE";
    public static final String COLUMN_CATEGORY_DESCRIPTION = "CATEGORY_DESCRIPTION";
    public static final String COLUMN_CATEGORY_AMOUNT = "CATEGORY_AMOUNT";
    public static final String COLUMN_ID = "ID";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "category.db", null, 1);
    }

    //this is called the first time a database is accessed. There should be a code in here to create a new database.
    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTableStatement = "CREATE TABLE " + CATEGORY_TABLE + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_CATEGORY_DESCRIPTION + " TEXT, " + COLUMN_CATEGORY_AMOUNT + " INT)";

        db.execSQL(createTableStatement);

    }

    //this is called if the database version number changes. It prevents previous users apps from breaking when you change the database design.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addOne(com.ptkzonelabs.rupeefy.Model.CategoryModel categoryModel) {

        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_CATEGORY_DESCRIPTION, categoryModel.getDescription());
        cv.put(COLUMN_CATEGORY_AMOUNT, categoryModel.getAmount());

        long insert = db.insert(CATEGORY_TABLE, null, cv);
        if (insert == -1) {
            return false;
        } else {
            return true;
        }
    }

    public List<com.ptkzonelabs.rupeefy.Model.CategoryModel> getEveryone() {

        List<com.ptkzonelabs.rupeefy.Model.CategoryModel> returnList = new ArrayList<>();

        //get data from the database

        String queryString = "SELECT * FROM " + CATEGORY_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst()) {
            //loop through the cursor (result set) and create new category objects. Put them into the return list.
            do {
                int categoryID = cursor.getInt(0);
                String categoryDescription = cursor.getString(1);
                int categoryAmount = cursor.getInt(2);

                com.ptkzonelabs.rupeefy.Model.CategoryModel newCategory = new com.ptkzonelabs.rupeefy.Model.CategoryModel(categoryID, categoryDescription, categoryAmount);
                returnList.add(newCategory);

            } while (cursor.moveToNext());
        } else {
            //failure. do not add anything to the list.
        }

        //close both the cursor and the db when done.
        cursor.close();
        db.close();
        return returnList;
    }
}
