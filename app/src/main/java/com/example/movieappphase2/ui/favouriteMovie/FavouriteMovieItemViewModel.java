package com.example.movieappphase2.ui.favouriteMovie;

import android.widget.ImageView;

import com.example.movieappphase2.utils.ItemClickListener;
import com.example.movieappphase2.data.model.Moviedata;

public class FavouriteMovieItemViewModel {
    private Moviedata item;
    private ItemViewModelListener itemViewModelListener;
    private ImageView imageView;

    public FavouriteMovieItemViewModel(Moviedata item, ItemViewModelListener itemViewModelListener, ImageView imageView) {
        this.item = item;
        this.itemViewModelListener = itemViewModelListener;
        this.imageView=imageView;
    }
    public void onItemClick() {
        itemViewModelListener.onItemClick(item,imageView);
    }

    public Moviedata getItem() {
        return item;
    }

    public interface ItemViewModelListener extends ItemClickListener {

    }
}
