package com.example.resipsedemo2;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import java.io.IOException;

import static maes.tech.intentanim.CustomIntent.customType;

public class Detail extends AppCompatActivity {

    private recipes recipe;
    private recipes arrival;
    TextView getIngredients, foodDetail, foodName, ingredients, recipesText;
    ImageView picture, addFavorite;
    SQLiteDatabase db;
    DatabaseCopyHelper databaseCopyHelper;
    Toolbar toolbar3;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    int recipesID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_detail);
        getIngredients =findViewById(R.id.malzemeGetir);
        Typeface kalin=Typeface.createFromAsset(getAssets(),"fonts/deneme1.otf");
        Typeface ince=Typeface.createFromAsset(getAssets(),"fonts/deneme2.otf");
        picture=findViewById(R.id.resim);
        foodDetail =findViewById(R.id.yemekDetay);
        foodName =findViewById(R.id.yemekadi);
        ingredients =findViewById(R.id.malzemeler);
        recipesText =findViewById(R.id.tariftext);
        foodDetail.setTypeface(ince);
        foodName.setTypeface(kalin);
        getIngredients.setTypeface(ince);
        ingredients.setTypeface(kalin);
        recipesText.setTypeface(kalin);
        addFavorite =findViewById(R.id.favoriekle);
        addFavorite.setBackgroundResource(R.drawable.ic_favorite_border_black_24dp);
        addFavorite.setTag((R.drawable.ic_favorite_border_black_24dp));
        toolbar3=findViewById(R.id.toolBar3);
        toolbar3.setTitle("");
        setSupportActionBar(toolbar3);
        recipe =(recipes) getIntent().getSerializableExtra("tarifbilgi");

        int a= (int) getIntent().getSerializableExtra("asd");


        System.out.println("geldi1");

        databaseCopyHelper=new DatabaseCopyHelper(this);
        try {
            databaseCopyHelper.createDataBase();
            System.out.println("geldi2");
            db=databaseCopyHelper.getWritableDatabase();
            Cursor c=db.rawQuery("select * from Yemektarifleri where id="+ recipe.getId()+" order by id desc",null);
            while (c.moveToNext()){

                arrival =new recipes(c.getInt(c.getColumnIndex("id")),
                        c.getString(c.getColumnIndex("yemekad")),
                        c.getString(c.getColumnIndex("hazirlamasuresi")),
                        c.getInt(c.getColumnIndex("kisisayisi")),
                        c.getString(c.getColumnIndex("resim")),
                        c.getString(c.getColumnIndex("malzeme")),
                        c.getString(c.getColumnIndex("yemektarifi")),
                        c.getInt(c.getColumnIndex("sure")),
                        c.getString(c.getColumnIndex("besindeger")));
                getIngredients.setText(c.getString(c.getColumnIndex("malzeme")));
                foodDetail.setText(c.getString(c.getColumnIndex("yemektarifi")));
                foodName.setText(c.getString(c.getColumnIndex("yemekad")));
                Picasso.with(Detail.this)
                        .load("https://i.hizliresim.com/" +c.getString(c.getColumnIndex("resim"))+".jpg")

                        .into(picture);
                recipesID =c.getInt(c.getColumnIndex("id"));

            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("hatalı"+e);

        }



        databaseCopyHelper=new DatabaseCopyHelper(getApplicationContext());
        try {
            databaseCopyHelper.createDataBase();
            System.out.println("geldi2");
            db=databaseCopyHelper.getWritableDatabase();
            Cursor c=db.rawQuery("select * from favoriler where favoriid="+ recipe.getId(),null);
            while (c.moveToNext()) {
                addFavorite.setBackgroundResource(R.drawable.ic_favorite_black_24dp);
                addFavorite.setTag((R.drawable.ic_favorite_black_24dp));

            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("hatalı"+e);

        }


        System.out.println("taglar"+ addFavorite.getTag());

        addFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if((Integer) addFavorite.getTag()==2131165299){
                    addFavorite.setBackgroundResource(R.drawable.ic_favorite_border_black_24dp);
                    addFavorite.setTag((R.drawable.ic_favorite_border_black_24dp));
                    System.out.println("açıksimge;"+ addFavorite.getTag());
                    db.execSQL("delete from favoriler where favoriid="+ recipe.getId());
                    Snackbar.make(v,"Favorilerden kaldırıldı",Snackbar.LENGTH_LONG).setAction("Favorilere Git", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent=new Intent(getBaseContext(), favorite.class);
                            startActivity(intent);
                        }
                    }).show();
                }
                else{
                    addFavorite.setBackgroundResource(R.drawable.ic_favorite_black_24dp);
                    addFavorite.setTag((R.drawable.ic_favorite_black_24dp));
                    System.out.println("kapalisimge;"+ addFavorite.getTag());
                    ContentValues cv = new ContentValues();
                    cv.put("favoriid", recipesID);

                    db.insert("favoriler",null,cv);
                    Snackbar.make(v,"Favorilere eklendi",Snackbar.LENGTH_LONG).setAction("Favorilere Git", new View.OnClickListener() {
                        @ Override
                        public void onClick(View v) {
                            Intent intent=new Intent(getBaseContext(), favorite.class);
                            startActivity(intent);
                        }
                    }).show();


                }




            }
        });




        System.out.println("sdsdsd;"+ addFavorite.getTag());

    }

    public void setSupportActionBar(Toolbar toolbar3) {
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
        customType(this, "bottom-to-up");


        return super.onKeyDown(keyCode, event);
    }
}

