package com.example.movieappphase2.ui.movie;


import android.content.Context;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.movieappphase2.R;
import com.example.movieappphase2.data.model.Moviedata;
import com.example.movieappphase2.databinding.FragmentMoviesBinding;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MoviesFragment extends Fragment {

    private MovieViewModel movieViewModel;
    MovieAdapter movieAdapter;
    String movieType;
    LayoutAnimationController controller=null;



    public MoviesFragment(String moviesType) {
        movieType=moviesType;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentMoviesBinding binding = DataBindingUtil.inflate(inflater,R.layout.fragment_movies,container,false);
        movieViewModel= ViewModelProviders.of(getActivity()).get(MovieViewModel.class);
        binding.setViewModel(movieViewModel);
        binding.setLifecycleOwner(this);
        binding.MoviesRecycleView.setLayoutManager(new GridLayoutManager(getActivity().getApplicationContext(),2));
        binding.MoviesRecycleView.setHasFixedSize(true);
        movieAdapter=new MovieAdapter();
        Context context=binding.MoviesRecycleView.getContext();
        controller= AnimationUtils.loadLayoutAnimation(context,R.anim.layout_animation_slide_bottom);
        binding.MoviesRecycleView.setLayoutAnimation(controller);
        binding.MoviesRecycleView.scheduleLayoutAnimation();
        binding.MoviesRecycleView.setAdapter(movieAdapter);
        movieViewModel.getMovies(movieType,1);
        //binding.movieLoading.setVisibility(View.VISIBLE);
        binding.movieLoading.setVisibility(View.GONE);


        return  binding.getRoot();
    }

}
