package com.softteco.roadqualitydetector.fragment;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.softteco.roadqualitydetector.RAApplication;
import com.softteco.roadqualitydetector.menu.ScreenItems;
import com.softteco.roadqualitydetector.sqlite.MeasurementsDataHelper;
import com.softteco.roadqualitydetector.sqlite.model.MeasurementItem;
import com.softteco.roadqualitydetector.sqlite.model.TagModel;

import java.util.List;

public class TagsMapFragment extends PopupMeasurementMapFragment {

    @Override
    protected void initMap(GoogleMap map) {
        super.initMap(map);
        map.setOnMarkerClickListener(this);
    }

    @Override
    protected void initMapData() {
        clearMap();
        long roadId = RAApplication.getInstance().getCurrentRoadId();
        MeasurementsDataHelper.getInstance().getTags(roadId,
        new MeasurementsDataHelper.MeasurementsDataLoaderListener<List<MeasurementItem>>() {
            @Override
            public void onDataLoaded(List<MeasurementItem> items) {
                onItemsReady(items, true);
            }
        });
    }

    @Override
    public int getTypeFragment() {
        return ScreenItems.SCREEN_TAGS;
    }

    @Override
    public boolean isHomeIndicatorMenu() {
        return true;
    }

    @Override
    protected void onPopupClicked(MeasurementItem item) {
        if (item instanceof TagModel) {
            openTagDetails((TagModel) item);
        }
    }

    public void openTagDetails(TagModel tag) {
        replaceFragment(TagDetailsFragment.newInstance(tag), true);
    }
}
