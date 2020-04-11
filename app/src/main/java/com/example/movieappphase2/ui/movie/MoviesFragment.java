package com.example.movieappphase2.ui.movie;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.movieappphase2.R;
import com.example.movieappphase2.data.model.Moviedata;
import com.example.movieappphase2.databinding.FragmentMoviesBinding;
import com.example.movieappphase2.ui.movieDetails.DetailsFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class MoviesFragment extends Fragment implements MovieAdapter.ItemAdapterListener {

    private MovieViewModel movieViewModel;
    private MovieAdapter movieAdapter;
    private String movieType;
    private LayoutAnimationController controller=null;
    private FragmentMoviesBinding binding;
    private int page =1;

    public MoviesFragment(String moviesType) {
        movieType=moviesType;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_movies,container,false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();

        setViewModel();
        initializeRecycleView();
        getMovies();
        binding.movieLoading.setVisibility(View.GONE);

        binding.moviesRecycleView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0)
                {
                    if(!movieType.equals("Favourite"))
                    if ((binding.moviesRecycleView.getAdapter().getItemCount()- 4) == ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition())
                        getMovies();}}});


        //binding.movieLoading.setVisibility(View.VISIBLE);

        return  binding.getRoot();
    }
    private void setViewModel(){
        movieViewModel= ViewModelProviders.of(getActivity()).get(MovieViewModel.class);
        binding.setViewModel(movieViewModel);
        binding.setLifecycleOwner(this);

    }

    private void initializeRecycleView(){

        binding.moviesRecycleView.setLayoutManager(new GridLayoutManager(getActivity().getApplicationContext(),2));
        binding.moviesRecycleView.setHasFixedSize(true);
        movieAdapter=new MovieAdapter();
        movieAdapter.setItemAdapterListener(this);
        animateRecycleView();
        binding.moviesRecycleView.setAdapter(movieAdapter);
    }
    private void animateRecycleView(){
        Context context=binding.moviesRecycleView.getContext();
        controller= AnimationUtils.loadLayoutAnimation(context,R.anim.layout_animation_slide_bottom);
        binding.moviesRecycleView.setLayoutAnimation(controller);
        binding.moviesRecycleView.scheduleLayoutAnimation();
    }
    private void getMovies(){

        if (!movieType.equals("Favourite")){
        movieViewModel.getMovies(movieType,page);
            Toast.makeText(getContext(),"here",Toast.LENGTH_LONG).show();
        page++;}
        else {
            movieViewModel.getFavouriteMovies(getContext());
        }
    }


    @Override
    public void onItemClick(Moviedata item, ImageView poster) {
        page=1;
        Fragment fragment = new DetailsFragment(item);
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addSharedElement(poster, ViewCompat.getTransitionName(poster)).setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }
}
