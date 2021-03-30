package com.example.resipsedemo2;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.CountDownTimer;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.squareup.picasso.Picasso;

import java.util.List;

import maes.tech.intentanim.CustomIntent;

public class recipesAdapter extends RecyclerView.Adapter<recipesAdapter.holderCardview>{
    private Context mContext;
    private List<recipes> recipesList;

    public recipesAdapter(Context mContext, List<recipes> recipesList) {
        this.mContext = mContext;
        this.recipesList = recipesList;
    }


    @Override
    public holderCardview onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview,parent,false);
        return new holderCardview(view);
    }

    @Override
    public void onBindViewHolder(final holderCardview holder, final int position) {
        final recipes recipes= recipesList.get(position);
        Typeface face=Typeface.createFromAsset(mContext.getAssets(),"fonts/deneme1.otf");
        Typeface face1=Typeface.createFromAsset(mContext.getAssets(),"fonts/deneme2.otf");
        holder.foodNameText.setTypeface(face);
        holder.PersonNumberText.setTypeface(face1);
        holder.makingTimeText.setTypeface(face1);
        holder.textView.setTypeface(face1);
        holder.caloriesText.setTypeface(face1);
        YoYo.with(Techniques.FadeInDown).playOn(holder.cardView); //aşagı dogru animasyon
        holder.foodNameText.setText(recipes.getFoodName());
        holder.makingTimeText.setText("Hazırlama: "+recipes.getPreparationTime()+"dk");
        holder.makingTimeText.setTypeface(face1);
        holder.caloriesText.setText(recipes.getCalories());
        String time=String.valueOf(recipes.getTime());
        if(recipes.getTime()==0){
            time="0";
        }
        holder.makingTimeText.setText(":"+time+" dk");
        holder.PersonNumberText.setText(String.valueOf(recipes.getNumberOfPerson()+" kisi"));


        Picasso.with(mContext)
                .load ("https://i.hizliresim.com/"+ recipesList.get(position).getPicture()+".jpg")
                .into(holder.picture);
            holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YoYo.with(Techniques.FadeOutLeft).playOn(holder.cardView);
                System.out.println(recipesList.get(position).getPicture());

                new CountDownTimer(750, 750) {

                    public void onTick(long millisUntilFinished) {

                    }

                    public void onFinish() {
                        Intent intent=new Intent(mContext, Detail.class);
                        intent.putExtra("tarifbilgi",recipes);
                        intent.putExtra("asd",mContext.getResources().getIdentifier(recipesList.get(position).getPicture()
                                ,"drawable", mContext.getPackageName()));
                        mContext.startActivity(intent);
                        CustomIntent.customType(mContext, "fadein-to-fadeout");


                    }

                }.start();

            }
        });


    }

    @Override
    public int getItemCount() {
        return recipesList.size();
    }

    public class holderCardview extends RecyclerView.ViewHolder {

        public CardView cardView;
        public TextView PersonNumberText, makingTimeText, foodNameText, caloriesText,getMakingTimeText,textView;

        private ImageView picture;





        public holderCardview(View itemView) {
            super(itemView);
            cardView=itemView.findViewById(R.id.cardview);
            PersonNumberText =itemView.findViewById(R.id.kisiSayisiText);
            getMakingTimeText =itemView.findViewById(R.id.hazirlamasuresiText);
            foodNameText =itemView.findViewById(R.id.yemekAdText);
            picture =itemView.findViewById(R.id.resim);
            makingTimeText =itemView.findViewById(R.id.hazirlamasuretext);
            textView=itemView.findViewById(R.id.textView);
            caloriesText =itemView.findViewById(R.id.besindegertext);


        }
    }
}


