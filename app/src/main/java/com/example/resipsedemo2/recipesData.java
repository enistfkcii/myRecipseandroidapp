package com.example.resipsedemo2;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
public class recipesData {
    public ArrayList<recipes> recipesArrayList(Database vt){
        ArrayList<recipes> tariflerArrayList=new ArrayList<>();

        SQLiteDatabase db=vt.getWritableDatabase();
        Cursor c=db.rawQuery("select * from Yemektarifleri ORDER BY RANDOM() LIMIT 3540",null);
        while (c.moveToNext()){
            recipes t=new recipes(c.getInt(c.getColumnIndex("id")),
                    c.getString(c.getColumnIndex("yemekad")),
                    c.getString(c.getColumnIndex("hazirlamasuresi")),
                    c.getInt(c.getColumnIndex("kisisayisi")),
                    c.getString(c.getColumnIndex("resim")),
                    c.getString(c.getColumnIndex("malzeme")),
                    c.getString(c.getColumnIndex("yemektarifi")),
                    c.getInt(c.getColumnIndex("sure")),
                    c.getString(c.getColumnIndex("besindeger")));
            tariflerArrayList.add(t);
        }
        return tariflerArrayList;

    }
    public ArrayList<recipes> searchRecipe (Database vt, String arananTarif){
        ArrayList<recipes> tariflerArrayList=new ArrayList<>();

        SQLiteDatabase db=vt.getWritableDatabase();
        Cursor c=db.rawQuery("select * from Yemektarifleri where yemekad like '%"+arananTarif+"%' ORDER BY RANDOM() LIMIT 3540",null);
        while (c.moveToNext()){
            recipes t=new recipes(c.getInt(c.getColumnIndex("id")),
                    c.getString(c.getColumnIndex("yemekad")),
                    c.getString(c.getColumnIndex("hazirlamasuresi")),
                    c.getInt(c.getColumnIndex("kisisayisi")),
                    c.getString(c.getColumnIndex("resim")),
                    c.getString(c.getColumnIndex("malzeme")),
                    c.getString(c.getColumnIndex("yemektarifi")),
                    c.getInt(c.getColumnIndex("sure")),
                    c.getString(c.getColumnIndex("besindeger")));
            tariflerArrayList.add(t);
        }
        return tariflerArrayList;



    }
    public ArrayList<recipes> searchFavorite (Database vt, String arananTarif){
        ArrayList<recipes> tariflerArrayList=new ArrayList<>();

        SQLiteDatabase db=vt.getWritableDatabase();
        Cursor c=db.rawQuery("select * from Yemektarifleri inner join favoriler on Yemektarifleri.id=favoriler.favoriid where yemekad like '%"+arananTarif+"%' ",null);
        while (c.moveToNext()){
            recipes t=new recipes(c.getInt(c.getColumnIndex("id")),
                    c.getString(c.getColumnIndex("yemekad")),
                    c.getString(c.getColumnIndex("hazirlamasuresi")),
                    c.getInt(c.getColumnIndex("kisisayisi")),
                    c.getString(c.getColumnIndex("resim")),
                    c.getString(c.getColumnIndex("malzeme")),
                    c.getString(c.getColumnIndex("yemektarifi")),
                    c.getInt(c.getColumnIndex("sure")),
                    c.getString(c.getColumnIndex("besindeger")));
            tariflerArrayList.add(t);
        }
        return tariflerArrayList;



    }
    public ArrayList<recipes> ArrayListFavorite (Database vt){
        ArrayList<recipes> RecipesArrayList=new ArrayList<>();

        SQLiteDatabase db=vt.getWritableDatabase();
        Cursor c=db.rawQuery("select * from Yemektarifleri inner join favoriler on Yemektarifleri.id=favoriler.favoriid",null);
        while (c.moveToNext()){
            recipes t=new recipes(c.getInt(c.getColumnIndex("id")),
                    c.getString(c.getColumnIndex("yemekad")),
                    c.getString(c.getColumnIndex("hazirlamasuresi")),
                    c.getInt(c.getColumnIndex("kisisayisi")),
                    c.getString(c.getColumnIndex("resim")),
                    c.getString(c.getColumnIndex("malzeme")),
                    c.getString(c.getColumnIndex("yemektarifi")),
                    c.getInt(c.getColumnIndex("sure")),
                    c.getString(c.getColumnIndex("besindeger")));
            RecipesArrayList.add(t);
        }
        return RecipesArrayList;

    }


}