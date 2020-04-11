package com.example.movieappphase2.ui.movieDetails;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.movieappphase2.R;
import com.example.movieappphase2.data.model.MovieDetails;
import com.example.movieappphase2.data.model.Moviedata;
import com.example.movieappphase2.databinding.FragmentDetailsBinding;
import com.example.movieappphase2.databinding.FragmentMoviesBinding;
import com.example.movieappphase2.ui.movieDetails.MovieDetailsViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsFragment extends Fragment {

    private MovieDetailsViewModel movieDetailsViewModel;
    private FragmentDetailsBinding binding;
    Moviedata moviedata=new Moviedata();
    private int movieId;

    public DetailsFragment(Moviedata item) {
        // Required empty public constructor
        moviedata=item;
        movieId=Integer.valueOf(item.getId());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_details,container,false);
        setViewModel();
        movieDetailsViewModel.getMovieDetails(movieId);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        setActionBar();

        binding.fabFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                movieDetailsViewModel.saveMovie(moviedata,getContext());
                binding.fabFavorite.setImageResource(R.drawable.ic_favorite);
                Toast.makeText(getContext(),"Add To Your Favourite ",Toast.LENGTH_LONG).show();
            }
        });



        return binding.getRoot();

    }

    private void setViewModel(){
        movieDetailsViewModel= ViewModelProviders.of(getActivity()).get(MovieDetailsViewModel.class);
        binding.setDetailsViewModel(movieDetailsViewModel);
        binding.setLifecycleOwner(this);
    }
    public void setActionBar(){

        ((AppCompatActivity)getActivity()).setSupportActionBar(binding.toolbar);

        if(((AppCompatActivity)getActivity()).getSupportActionBar() != null)
            ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
}
