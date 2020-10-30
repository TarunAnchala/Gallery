package com.tarun.gallery.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.tarun.gallery.R;
import com.tarun.gallery.data.Image;
import com.tarun.gallery.utils.Utils;
import com.tarun.gallery.viewModel.ImageListViewModel;

/**
 * Fragment to display full Image on click of recycler view item
 */
public class FullScreenImageFragment extends Fragment {

    public static final String TAG = "FullScreenImageFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.image_full_screen_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageView imageView = view.findViewById(R.id.fullScreenImageView);
        if (getArguments() != null) {
            ImageListViewModel viewModel = new ViewModelProvider(requireActivity()).get(ImageListViewModel.class);
            Image image = viewModel.getImageObjBasedOnPos(getArguments().getInt(Utils.POSITION));
            Glide.with(this).load(image.getDownloadUrl()).thumbnail(0.2f).placeholder(R.drawable.default_img).into(imageView);
        }
    }


}
