package com.example.movieappphase2.ui.movie;

import com.example.movieappphase2.data.model.Moviedata;

public class MovieItemViewModel {
    Moviedata item;

    public MovieItemViewModel(Moviedata moviedata) {
        this.item = moviedata;
    }

    public Moviedata getItem() {
        return item;
    }
}
