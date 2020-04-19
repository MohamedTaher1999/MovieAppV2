package com.example.movieappphase2.ui.favouriteMovie;

import android.content.Context;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.movieappphase2.data.ApiRepository;
import com.example.movieappphase2.data.DatabaseRepository;
import com.example.movieappphase2.data.model.Moviedata;

import java.util.ArrayList;

public class FavouriteMovieViewModel extends ViewModel {



    private DatabaseRepository databaseRepository;
    private MutableLiveData<ArrayList<Moviedata>> movies;
    private ObservableField<Boolean> dataReady;

    public FavouriteMovieViewModel() {
        movies=new MutableLiveData<>();
        databaseRepository=new DatabaseRepository();
        dataReady=new ObservableField<>(false);
    }



    public void getFavouriteMovies(Context context){

        databaseRepository.getStoredMovies().observeForever(new Observer<ArrayList<Moviedata>>() {
            @Override
            public void onChanged(ArrayList<Moviedata> moviedata) {
                movies.setValue(moviedata);
                dataReady.set(true);
            }
        });
        databaseRepository.getFavouriteMovies(context);
    }

    public ObservableField<Boolean> getDataReady() {
        return dataReady;
    }
    public MutableLiveData<ArrayList<Moviedata>> getListMovies() {
        return movies;
    }

    public void setMovies(MutableLiveData<ArrayList<Moviedata>> movies) {
        this.movies = movies;
    }
}
