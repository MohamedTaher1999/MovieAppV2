package com.example.movieappphase2.ui.movie;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.movieappphase2.data.ApiRepository;
import com.example.movieappphase2.data.DatabaseRepository;
import com.example.movieappphase2.data.model.Moviedata;

import java.util.ArrayList;

public class MovieViewModel extends ViewModel {

    private ApiRepository apiRepository;
    private DatabaseRepository databaseRepository;
    private MutableLiveData<ArrayList<Moviedata>> movies;
    private int page=0;
    public MovieViewModel() {
        apiRepository=new ApiRepository();
        movies=new MutableLiveData<>();
        databaseRepository=new DatabaseRepository();



    }


    public void getMovies(String category,int page){

        if(this.page==1){
            movies.setValue(new ArrayList<Moviedata>());
        }
        this.page=page;
        movies=apiRepository.getListMovies();
        apiRepository.getMovies(category,page);
    }
    public void getFavouriteMovies(Context context){
        movies.setValue(new ArrayList<Moviedata>());
        movies=databaseRepository.getStoredMovies();
        databaseRepository.getFavouriteMovies(context);
    }

    public MutableLiveData<ArrayList<Moviedata>> getListMovies() {
        return movies;
    }

    public void setMovies(MutableLiveData<ArrayList<Moviedata>> movies) {
        this.movies = movies;
    }
}
