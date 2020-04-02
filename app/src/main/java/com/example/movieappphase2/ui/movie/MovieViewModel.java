package com.example.movieappphase2.ui.movie;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.movieappphase2.data.ApiRepository;
import com.example.movieappphase2.data.model.Moviedata;

import java.util.ArrayList;

public class MovieViewModel extends ViewModel {

    private ApiRepository apiRepository;
    private MutableLiveData<ArrayList<Moviedata>> allMovies;

    public MovieViewModel() {
        apiRepository=new ApiRepository();
        allMovies=new MutableLiveData<>();

        allMovies=apiRepository.getListMovies();

    }
    public void getMovies(String category, int page){

        apiRepository.getMovies(category,page);
    }

    public MutableLiveData<ArrayList<Moviedata>> getListMovies() {
        return allMovies;
    }
}
