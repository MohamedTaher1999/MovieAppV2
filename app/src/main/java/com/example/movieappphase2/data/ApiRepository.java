package com.example.movieappphase2.data;

import androidx.lifecycle.MutableLiveData;

import com.example.movieappphase2.data.model.Moviedata;
import com.example.movieappphase2.data.model.ResultsMovies;
import com.example.movieappphase2.data.network.ApiService;
import com.example.movieappphase2.helper.ApiMovieHelper;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiRepository {
    private MutableLiveData<ArrayList<Moviedata>> Movies;
    public ApiRepository() {
        Movies=new MutableLiveData<>();
    }

    public MutableLiveData<ArrayList<Moviedata>> getListMovies() {
        return Movies;
    }

    public void getMovies(String category, int page) {

        ApiService.getAPI()
                .getMovies(category,ApiService.key,ApiService.LANGUAGE,page)
                .enqueue(new Callback<ResultsMovies>() {
                    @Override
                    public void onResponse(Call<ResultsMovies> call, Response<ResultsMovies> response) {
                        Movies.setValue( response.body().getResults());

                    }

                    @Override
                    public void onFailure(Call<ResultsMovies> call, Throwable t) {
                        try {
                            throw new InterruptedException("Something went wrong!");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            System.out.println("nnoooooooooo");

                        }
                    }
                });
    }






}
