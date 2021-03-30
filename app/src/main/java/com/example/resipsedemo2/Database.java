package com.example.resipsedemo2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {

    public Database(Context context) {
        super(context, "recipes.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE IF NOT EXISTS\"Yemektarifleri\" (\n" +
                "\t\"id\"\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "\t\"kategori\"\tVARCHAR,\n" +
                "\t\"yemekad\"\tVARCHAR,\n" +
                "\t\"resim\"\tVARCHAR,\n" +
                "\t\"yemektarifi\"\tTEXT,\n" +
                "\t\"hazirlamasuresi\"\tINTEGER,\n" +
                "\t\"sure\"\tINTEGER,\n" +
                "\t\"besindeger\"\tVARCHAR,\n" +
                "\t\"kisisayisi\"\tINTEGER,\n" +
                "\t\"malzeme\"\tTEXT\n" +
                ");");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS Yemektarifleri");

    }
}