package com.example.movieappphase2.ui.favouriteMovie;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.movieappphase2.utils.ItemClickListener;
import com.example.movieappphase2.data.model.Moviedata;
import com.example.movieappphase2.databinding.FavouriteMovieItemBinding;
import java.util.ArrayList;

public class FavouriteMovieAdapter extends RecyclerView.Adapter<FavouriteMovieAdapter.ItemViewHolder> {

    private ArrayList<Moviedata> favouriteMovies;
    ItemAdapterListener itemAdapterListener;

    public void setItemAdapterListener(ItemAdapterListener itemAdapterListener) {
        this.itemAdapterListener = itemAdapterListener;
    }

    public FavouriteMovieAdapter() {
        favouriteMovies=new ArrayList<>();
    }
    public void clearItems(){

        favouriteMovies.clear();
    }
    public void addItems(ArrayList<Moviedata> items){
        if(items!=null){
            this.favouriteMovies.addAll(items);
            notifyDataSetChanged();}

    }
    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        FavouriteMovieItemBinding favouriteMovieItemBinding = FavouriteMovieItemBinding.inflate(layoutInflater, parent, false);
        return new ItemViewHolder(favouriteMovieItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        if(favouriteMovies.size()>0){
            holder.onBindItem(favouriteMovies.get(position));}
    }

    @Override
    public int getItemCount() {
        return favouriteMovies != null && favouriteMovies.size() > 0 ? favouriteMovies.size() : 1;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements FavouriteMovieItemViewModel.ItemViewModelListener  {

        FavouriteMovieItemBinding itemBinding;


        public ItemViewHolder(@NonNull FavouriteMovieItemBinding itemBinding) {
            super(itemBinding.getRoot());


            this.itemBinding=itemBinding;
        }
        public void onBindItem(Moviedata item) {
            // set Data to variable to set each specific Item
            itemBinding.setFavouritemoveitem(new FavouriteMovieItemViewModel(item,this,itemBinding.filmposter));

            itemBinding.executePendingBindings();
        }


        @Override
        public void onItemClick(Moviedata item, ImageView poster) {
            if(item!=null)
                itemAdapterListener.onItemClick(item,poster);
        }
    }
    public interface ItemAdapterListener extends ItemClickListener {}

}
