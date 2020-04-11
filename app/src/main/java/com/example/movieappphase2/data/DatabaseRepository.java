package com.example.movieappphase2.data;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import com.example.movieappphase2.data.database.MyAppDatabase;
import com.example.movieappphase2.data.model.Moviedata;

import java.util.ArrayList;

public class DatabaseRepository {
    private MyAppDatabase myAppDatabase;
    private MutableLiveData<ArrayList<Moviedata>> storedMovies;

    public MutableLiveData<ArrayList<Moviedata>> getStoredMovies() {
        return storedMovies;
    }

    public DatabaseRepository() {
        storedMovies=new MutableLiveData<>();
    }

    public void getFavouriteMovies(Context context){
        myAppDatabase= Room.databaseBuilder(context,MyAppDatabase.class,"Moviedb").allowMainThreadQueries().build();
        storedMovies.setValue(new ArrayList<Moviedata>(myAppDatabase.myDoa().getMovies()));
        System.out.println(String.valueOf(storedMovies.getValue().size()));


    }
    public void saveFavouriteMovies(Context context,Moviedata moviedata){
        myAppDatabase= Room.databaseBuilder(context,MyAppDatabase.class,"Moviedb").allowMainThreadQueries().build();
        myAppDatabase.myDoa().addMovie(moviedata);
    }



}
