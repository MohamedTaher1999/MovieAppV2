package com.example.movieappphase2.utils;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.movieappphase2.data.model.Moviedata;
import com.example.movieappphase2.ui.movie.MovieAdapter;

import java.util.ArrayList;

public class BindingUtils {

    @BindingAdapter({"adapter"})
    public static void setRecyclerViewData(RecyclerView recyclerView, ArrayList<Moviedata> items) {
        MovieAdapter movieAdapter= (MovieAdapter)recyclerView.getAdapter();
        if(movieAdapter!=null&&items!=null){
            movieAdapter.clearItems();
            movieAdapter.addItems(items);
            System.out.println("DDDDDDDDDDdd");


        }

    }
    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String url) {
        Glide.with(imageView.getContext())
                .load(AppConstants.imageUrl+url)
                .into(imageView);
    }



}
