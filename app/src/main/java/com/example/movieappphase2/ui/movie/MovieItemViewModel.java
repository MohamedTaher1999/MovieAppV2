package com.example.movieappphase2.ui.movie;

import android.media.Image;
import android.widget.ImageView;

import com.example.movieappphase2.ItemClickListener;
import com.example.movieappphase2.data.model.Moviedata;

public class MovieItemViewModel {
    private Moviedata item;
    private ItemViewModelListener itemViewModelListener;
    private ImageView imageView;

    public MovieItemViewModel(Moviedata item, ItemViewModelListener itemViewModelListener, ImageView imageView) {
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
