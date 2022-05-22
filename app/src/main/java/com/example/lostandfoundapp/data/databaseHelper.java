package com.example.lostandfoundapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.lostandfoundapp.model.LostFoundMod;
import com.example.lostandfoundapp.ulti.UltiClass;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

public class databaseHelper extends SQLiteOpenHelper {


    public databaseHelper(@Nullable Context context) {
        super(context, UltiClass.DATABASE_NAME, null, UltiClass.DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_LOST_FOUND_TABLE = "CREATE TABLE " + UltiClass.TABLE_NAME + "("
                + UltiClass.ITEM_ID + " " + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + UltiClass.TYPE + " TEXT, "
                + UltiClass.NAME + " TEXT, " + UltiClass.PHONE + " TEXT, "
                + UltiClass.DESCRIPTION + " TEXT, "
                + UltiClass.DATE + " TEXT, "
                + UltiClass.LOCATION + " TEXT );";
        db.execSQL(CREATE_LOST_FOUND_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String DROP_LOST_FOUND_TABLE = "DROP TABLE IF EXISTS";
        db.execSQL(DROP_LOST_FOUND_TABLE, new String[]{UltiClass.TABLE_NAME});

        onCreate(db);
    }

    public long insertLostFound(LostFoundMod lostFound)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(UltiClass.TYPE, lostFound.getType());
        contentValues.put(UltiClass.NAME, lostFound.getName());
        contentValues.put(UltiClass.PHONE, lostFound.getPhone());
        contentValues.put(UltiClass.DESCRIPTION, lostFound.getDescription());
        contentValues.put(UltiClass.DATE, lostFound.getDate());
        contentValues.put(UltiClass.LOCATION, lostFound.getLocation());
        long newRowId = db.insert(UltiClass.TABLE_NAME, null, contentValues);
        db.close();
        return newRowId;
    }

    /*public boolean fetchLostFound(String name, String phone, String date, String description,
                                  String location, String type)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(UltiClass.TABLE_NAME, new String[]{UltiClass.ITEM_ID}, UltiClass.TYPE + "=? and "
                        + UltiClass.NAME + "=? and " + UltiClass.PHONE + "=? and " + UltiClass.DESCRIPTION + "=? and "
                        + UltiClass.DATE + "=? and " + UltiClass.LOCATION + "=?",
                new String[]{type, name, phone, date, description, location}, null, null, null);
        int numberOfRows = cursor.getCount();
        db.close();

        if (numberOfRows > 0)
            return true;
        else
            return false;

    }*/

    public List<LostFoundMod> fetchAllItems()
    {
        List<LostFoundMod> LostFoundList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String selectAll = " SELECT * FROM " + UltiClass.TABLE_NAME;
        Cursor cursor = db.rawQuery(selectAll, null);

        if (cursor.moveToFirst())
        {
            do {
                LostFoundMod lostFound = new LostFoundMod();

                lostFound.setItem_ID(cursor.getInt(0));
                lostFound.setType((cursor.getString(1)));
                lostFound.setName((cursor.getString(2)));
                lostFound.setPhone((cursor.getString(3)));
                lostFound.setDescription((cursor.getString(4)));
                lostFound.setDate((cursor.getString(5)));
                lostFound.setLocation((cursor.getString(6)));

                LostFoundList.add(lostFound);
            }
            while (cursor.moveToNext());
        }
        return LostFoundList;
    }


    public void deleteLostFound (String name, String phone, String date)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL( " DELETE FROM " + UltiClass.TABLE_NAME + " WHERE " + UltiClass.NAME + "= '" + name + "' AND "
                     + UltiClass.PHONE + " = '" + phone + "' AND " + UltiClass.DATE + "= '" + date + "'");
    }
}
