package com.example.movieappphase2.ui.movieDetails;

import android.content.Context;
import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.movieappphase2.data.ApiRepository;
import com.example.movieappphase2.data.DatabaseRepository;
import com.example.movieappphase2.data.model.MovieDetails;
import com.example.movieappphase2.data.model.Moviedata;

import java.util.ArrayList;

public class MovieDetailsViewModel extends ViewModel {
    private ApiRepository apiRepository;
    private DatabaseRepository databaseRepository;
    private MutableLiveData<MovieDetails> movieDetails;

    public MovieDetailsViewModel() {
        apiRepository=new ApiRepository();
        movieDetails=new MutableLiveData<>();
        movieDetails=apiRepository.getMovieDetailsLiveData();
        databaseRepository=new DatabaseRepository();
    }
    public void saveMovie(Moviedata moviedata, Context context){
        databaseRepository.saveFavouriteMovies(context,moviedata);

    }

    public void getMovieDetails(int id){

        apiRepository.getMovieDetails(id);
    }

    public MutableLiveData<MovieDetails> getMovieDetailsLiveData() {
        return movieDetails;
    }
}
