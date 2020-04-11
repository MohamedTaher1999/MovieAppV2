package com.example.movieappphase2.utils;

import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.movieappphase2.R;
import com.example.movieappphase2.data.model.Genres;
import com.example.movieappphase2.data.model.Moviedata;
import com.example.movieappphase2.ui.movie.MovieAdapter;

import java.util.ArrayList;
import java.util.List;

public class BindingUtils {

    @BindingAdapter({"adapter"})
    public static void setRecyclerViewData(RecyclerView recyclerView, ArrayList<Moviedata> items) {
        MovieAdapter movieAdapter= (MovieAdapter)recyclerView.getAdapter();
        if(movieAdapter!=null&&items!=null){

            movieAdapter.addItems(items);

        }

    }
    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String url) {
        Glide.with(imageView.getContext())
                .load(AppConstants.imageUrl+url)
                .into(imageView);
    }

    @BindingAdapter("setMovieCategory")
    public static void setMovieCategoty(TextView textView, List<Genres> genres){

        if(genres!=null)
        for(int i=0;i<genres.size();i++){
            if(textView.getText()!=""){
                textView.setText(textView.getText()+","+genres.get(i).getName());}
            else{
                textView.setText(genres.get(i).getName());}

        }
    }

    @BindingAdapter("setMovieStatueImage")
    public static void setMovieStatueImage(ImageView image,String IsReleased){
        if(IsReleased!=null){
        if(IsReleased.equals("Released")){image.setImageResource(R.drawable.ic_released);}
        else{image.setImageResource(R.drawable.ic_un_released);}}


    }

    @BindingAdapter("setRate")
    public static void setRate(RatingBar ratingBar,double rate){

        ratingBar.setRating((float) rate/2);


    }
    @BindingAdapter("setInteger")
    public static void setInteger(TextView textView ,int number){
        textView.setText(String.valueOf(number));

    }

    @BindingAdapter("setAdultText")
    public static void setAdultText(TextView textView ,boolean check){
        if(check){textView.setText("Yes");} else{textView.setText("No");}


    }



}
