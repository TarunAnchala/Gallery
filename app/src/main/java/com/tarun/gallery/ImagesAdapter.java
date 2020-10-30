package com.tarun.gallery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.tarun.gallery.data.Image;
import com.tarun.gallery.utils.InjectManager;

import java.util.ArrayList;

/**
 * Adapter class to display list of images
 */
public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.ViewHolder> {

    private final RequestManager glide;
    private LayoutInflater layoutInflater;
    private ArrayList<Image> listOfImages;
    private static final String TAG = "ImagesAdapter";

    public ImagesAdapter(Context context) {
        glide = Glide.with(context);
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.image_adapter_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        glide.load(listOfImages.get(position).getDownloadUrl()).placeholder(R.drawable.default_img).thumbnail(0.2f).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.getImageView());

    }


    /**
     * API to set Data for adapter
     *
     * @param listOfImages
     */
    public void setData(ArrayList<Image> listOfImages) {
        if (this.listOfImages != null) {
            this.listOfImages.clear();
        }
        this.listOfImages = listOfImages;
        notifyDataSetChanged();
    }

  
    @Override
    public int getItemCount() {
        return listOfImages != null ? listOfImages.size() : 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageItem);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    InjectManager.getInstance().inject(InjectManager.LAUNCH_DETAIL_SCREEN, getAdapterPosition());
                }
            });
        }

        public ImageView getImageView() {
            return imageView;
        }
    }
}
