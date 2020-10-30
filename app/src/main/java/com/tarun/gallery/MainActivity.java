package com.tarun.gallery;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.tarun.gallery.fragments.FullScreenImageFragment;
import com.tarun.gallery.repository.DataManager;
import com.tarun.gallery.utils.InjectManager;
import com.tarun.gallery.utils.Utils;
import com.tarun.gallery.viewModel.ImageListViewModel;

public class MainActivity extends AppCompatActivity implements InjectManager.InjectedEventNotifier {
    private static final String TAG = "MainActivity";
    private RecyclerView imagesRecyclerView;
    private LottieAnimationView animationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        animationView = (LottieAnimationView) findViewById(R.id.animationView);
        ImageListViewModel viewModel = new ViewModelProvider(this).get(ImageListViewModel.class);

        imagesRecyclerView = findViewById(R.id.imagesRecyclerView);
        imagesRecyclerView.setHasFixedSize(true);
        imagesRecyclerView.setItemViewCacheSize(50);
        imagesRecyclerView.setDrawingCacheEnabled(true);
        imagesRecyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        imagesRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        ImagesAdapter imagesAdapter = new ImagesAdapter(this);
        imagesRecyclerView.setAdapter(imagesAdapter);

        viewModel.getImagesLiveData().observe(this, listOfImages -> {
            animationView.setVisibility(View.GONE);
            if (listOfImages != null && listOfImages.size() > 0) {
                imagesAdapter.setData(listOfImages);
            } else {
                if (!Utils.isNetworkAvailable()) {
                    displayToast(R.string.network_not_available);
                } else {
                    displayToast(R.string.data_not_available);
                }
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        InjectManager.getInstance().addListener(InjectManager.LAUNCH_DETAIL_SCREEN, this);
    }

    @Override
    public void onReceiveEvent(int eventType, Object object) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        int position = (int) object;
        Bundle bundle = new Bundle();
        bundle.putInt(Utils.POSITION, position);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        FullScreenImageFragment detailScreen = new FullScreenImageFragment();
        detailScreen.setArguments(bundle);
        fragmentTransaction.replace(R.id.container, detailScreen, FullScreenImageFragment.TAG);
        fragmentTransaction.addToBackStack(FullScreenImageFragment.TAG);
        fragmentTransaction.commit();
    }

    /**
     * Method to display Toast
     *
     * @param textToBeDisplayed
     */
    private void displayToast(int textToBeDisplayed) {
        Toast.makeText(getApplicationContext(), textToBeDisplayed, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        InjectManager.getInstance().removeListener(InjectManager.LAUNCH_DETAIL_SCREEN, this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        DataManager.deleteInstance();
    }
}