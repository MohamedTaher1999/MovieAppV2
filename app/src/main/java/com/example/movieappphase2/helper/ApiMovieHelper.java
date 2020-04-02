package com.example.movieappphase2.helper;

import com.example.movieappphase2.data.model.MovieDetails;
import com.example.movieappphase2.data.model.Moviedata;

import java.util.ArrayList;

public interface ApiMovieHelper {

    void setMoviesData(ArrayList<Moviedata> Movies);
    void setMovieDetailsData(MovieDetails movieDetails);
}
