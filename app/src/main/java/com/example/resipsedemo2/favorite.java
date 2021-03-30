package com.example.resipsedemo2;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;



import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.SearchView;
import androidx.core.view.MenuItemCompat;
import androidx.appcompat.widget.Toolbar;

import java.io.IOException;
import java.util.ArrayList;
public class favorite extends AppCompatActivity implements SearchView.OnQueryTextListener{
    Toolbar toolbar1;
    RecyclerView rv1;
    ArrayList<recipes> recipesList;
    private recipesAdapter adapterr;
    private Database vtt;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_favorite);
        veritabaniKopyala();
        toolbar1=findViewById(R.id.toolBar1);
        rv1=findViewById(R.id.rv1);
        rv1.setHasFixedSize(true);
        rv1.setLayoutManager(new LinearLayoutManager(this));

        vtt=new Database(this);


        recipesList =new recipesData().ArrayListFavorite(vtt);


        adapterr=new recipesAdapter(this, recipesList);
        rv1.setAdapter(adapterr);

        toolbar1.setTitle("Favorilerim");
        setSupportActionBar(toolbar1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        getMenuInflater().inflate(R.menu.menu,menu);
        MenuItem menuItem=menu.findItem(R.id.searchFood);
        SearchView searchView= (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(this);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {

        System.out.println("onQueryTextSubmit:"+query);
        aramaYap(query);


        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        System.out.println("onQueryTextChange:"+newText);

        aramaYap(newText);
        return false;
    }

    public void veritabaniKopyala(){
        DatabaseCopyHelper databaseCopyHelper=new DatabaseCopyHelper(this);


        try {
            databaseCopyHelper.createDataBase();
        } catch (IOException e) {

        }


    }
    public void aramaYap(String searchKelime){
        recipesList =new recipesData().searchFavorite(vtt,searchKelime);


        adapterr=new recipesAdapter(this, recipesList);
        rv1.setAdapter(adapterr);

    }
}
