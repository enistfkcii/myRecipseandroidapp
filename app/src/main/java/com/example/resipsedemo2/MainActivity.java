package com.example.resipsedemo2;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    Toolbar toolbar;
    RecyclerView rv;
    ArrayList<recipes> recipesList;
    private recipesAdapter adapter;
    private Database vt;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        vt=new Database(this);
        copyDatabase();
        toolbar=findViewById(R.id.toolBar);
        rv=findViewById(R.id.rv);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));

        button=findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getBaseContext(), favorite.class);
                startActivity(intent);
            }
        });



        recipesList =new recipesData().recipesArrayList(vt);


        adapter=new recipesAdapter(this, recipesList);
        rv.setAdapter(adapter);

        toolbar.setTitle("");
        setSupportActionBar(toolbar);

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

    public void copyDatabase(){
        DatabaseCopyHelper databaseCopyHelper=new DatabaseCopyHelper(this);


        try {
            databaseCopyHelper.createDataBase();
        } catch (IOException e) {

        }


    }

    public void aramaYap(String searchKelime){
        recipesList =new recipesData().searchRecipe(vt,searchKelime);


        adapter=new recipesAdapter(this, recipesList);
        rv.setAdapter(adapter);

    }
}
