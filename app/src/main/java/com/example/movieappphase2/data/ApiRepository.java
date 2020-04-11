package com.example.movieappphase2.data;

import androidx.lifecycle.MutableLiveData;

import com.example.movieappphase2.data.model.MovieDetails;
import com.example.movieappphase2.data.model.Moviedata;
import com.example.movieappphase2.data.model.ResultsMovies;
import com.example.movieappphase2.data.network.ApiService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiRepository {
    private MutableLiveData<ArrayList<Moviedata>> movies;
    private MutableLiveData<MovieDetails> movieDetails;

    public ApiRepository() {
        movies=new MutableLiveData<>();
        movieDetails=new MutableLiveData<>();
    }

    public MutableLiveData<MovieDetails> getMovieDetailsLiveData() {
        return movieDetails;
    }

    public MutableLiveData<ArrayList<Moviedata>> getListMovies() {
        return movies;
    }

    public void getMovies(String category, int page) {
        System.out.println(category);
        ApiService.getAPI()
                .getMovies(category,ApiService.key,ApiService.LANGUAGE,page)
                .enqueue(new Callback<ResultsMovies>() {
                    @Override
                    public void onResponse(Call<ResultsMovies> call, Response<ResultsMovies> response) {
                        movies.setValue( response.body().getResults());

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

    public  void getMovieDetails(int id){


        ApiService
                .getAPI()
                .getMovieDetails(id,ApiService.key,ApiService.LANGUAGE)
                .enqueue(new Callback<MovieDetails>() {
                    @Override
                    public void onResponse(Call<MovieDetails> call, Response<MovieDetails> response) {
                        movieDetails.setValue(response.body());
                    }
                    @Override
                    public void onFailure(Call<MovieDetails> call, Throwable t) {

                    }
                });




    }







}
