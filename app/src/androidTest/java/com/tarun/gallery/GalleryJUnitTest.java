package com.tarun.gallery;

import androidx.lifecycle.ViewModelProvider;
import androidx.test.rule.ActivityTestRule;

import com.tarun.gallery.repository.DataManager;
import com.tarun.gallery.viewModel.ImageListViewModel;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class GalleryJUnitTest {


    @ClassRule
    public static final ActivityTestRule<MainActivity> activity = new ActivityTestRule<>(MainActivity.class);
    private static ImageListViewModel imageListViewModel;
    private static DataManager dataManager;

    @BeforeClass
    public static void initialize() {
        imageListViewModel = new ViewModelProvider(activity.getActivity()).get(ImageListViewModel.class);
        dataManager = DataManager.getInstance();
    }


    @Test
    public void testImagesLiveData() {
        assertNotNull(imageListViewModel.getImagesLiveData());
    }

    @Test
    public void testDataManagerLiveData() {
        assertNotNull(dataManager.getImagesLiveData());
    }

    @Test
    public void testViewModelAndDataManagerData() {
        if (dataManager.getImagesLiveData().getValue() != null && imageListViewModel.getImagesLiveData().getValue() != null) {
            assertEquals(dataManager.getImagesLiveData().getValue().size(), imageListViewModel.getImagesLiveData().getValue().size());
        }
    }

    @Test
    public void testViewModelAndDataManagerDataObjs() {
        if (dataManager.getImagesLiveData().getValue() != null && imageListViewModel.getImagesLiveData().getValue() != null) {
            assertTrue(dataManager.getImagesLiveData().getValue().size() > 0);
            assertTrue(imageListViewModel.getImagesLiveData().getValue().size() > 0);
            assertEquals(dataManager.getImagesLiveData().getValue().get(0), imageListViewModel.getImagesLiveData().getValue().get(0));
        }
    }

    @Test
    public void testImageObjBasedOnPosAPI() {
        if (imageListViewModel.getImagesLiveData().getValue() != null && imageListViewModel.getImagesLiveData().getValue().size() > 0) {
            assertNotNull(imageListViewModel.getImageObjBasedOnPos(0));
        }
    }

    @Test
    public void testImageObjBasedOnPosAPIData() {
        if (imageListViewModel.getImagesLiveData().getValue() != null && imageListViewModel.getImagesLiveData().getValue().size() > 0) {
            assertEquals(imageListViewModel.getImagesLiveData().getValue().get(0), imageListViewModel.getImageObjBasedOnPos(0));
        }
    }

    @AfterClass
    public static void tearDown() {
        imageListViewModel = null;
        dataManager = null;
    }
}
