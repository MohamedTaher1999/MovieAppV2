package com.example.movieappphase2.ui.favouriteMovie;

import android.content.Context;
import android.os.Bundle;

import androidx.core.view.ViewCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.movieappphase2.R;
import com.example.movieappphase2.data.model.Moviedata;
import com.example.movieappphase2.databinding.FragmentFavouriteMovieBinding;
import com.example.movieappphase2.ui.movieDetails.DetailsFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavouriteMovieFragment extends Fragment implements FavouriteMovieAdapter.ItemAdapterListener {

    private FavouriteMovieViewModel favouriteMovieViewModel;
    private FavouriteMovieAdapter favouriteMovieAdapter;
    private LayoutAnimationController controller=null;
    private FragmentFavouriteMovieBinding binding;

    public FavouriteMovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_favourite_movie,container,false);
        setViewModel();
        initializeRecycleView();
        getMovies();

        return binding.getRoot();

    }
    private void setViewModel(){
        favouriteMovieViewModel= ViewModelProviders.of(this).get(FavouriteMovieViewModel.class);
        binding.setFavouriteviewModel(favouriteMovieViewModel);
        binding.setLifecycleOwner(this);

    }
    private void initializeRecycleView(){

        binding.moviesRecycleView.setLayoutManager(new GridLayoutManager(getActivity().getApplicationContext(),2));
        binding.moviesRecycleView.setHasFixedSize(true);
        favouriteMovieAdapter=new FavouriteMovieAdapter();
        favouriteMovieAdapter.setItemAdapterListener(this);
        animateRecycleView();
        binding.moviesRecycleView.setAdapter(favouriteMovieAdapter);
    }
    private void animateRecycleView(){
        Context context=binding.moviesRecycleView.getContext();
        controller= AnimationUtils.loadLayoutAnimation(context,R.anim.layout_animation_slide_bottom);
        binding.moviesRecycleView.setLayoutAnimation(controller);
        binding.moviesRecycleView.scheduleLayoutAnimation();
    }
    private void getMovies(){



            favouriteMovieViewModel.getFavouriteMovies(getContext());

    }
    @Override
    public void onItemClick(Moviedata item, ImageView poster) {
        Fragment fragment = new DetailsFragment(item);
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addSharedElement(poster, ViewCompat.getTransitionName(poster)).setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
    @Override
    public void onStart(){
        super.onStart();
        Toast.makeText(getContext(),"noeee",Toast.LENGTH_LONG).show();

    }
}
