package com.softteco.roadqualitydetector.fragment;

import com.google.android.gms.maps.model.Marker;
import com.softteco.roadqualitydetector.menu.ScreenItems;
import com.softteco.roadqualitydetector.sqlite.model.BumpModel;
import com.softteco.roadqualitydetector.sqlite.model.FolderModel;
import com.softteco.roadqualitydetector.sqlite.model.MeasurementItem;
import com.softteco.roadqualitydetector.sqlite.MeasurementsDataHelper;

import java.util.List;

public class RoadMapFragment extends PopupMeasurementMapFragment {

    private FolderModel folder;

    @Override
    protected void checkArgs() {
        if (getArguments() != null && getArguments().getSerializable(RoadListFragment.ARG_ROAD_LIST) != null) {
            folder = (FolderModel) getArguments().getSerializable(RoadListFragment.ARG_ROAD_LIST);
        }
    }

    @Override
    protected void initMapData() {
        MeasurementsDataHelper.getInstance().
                getData(folder, new MeasurementsDataHelper.MeasurementsDataLoaderListener<List<MeasurementItem>>() {
                    @Override
                    public void onDataLoaded(List<MeasurementItem> items) {
                        onItemsReady(items, true);
                    }
                });
    }

    @Override
    public int getTypeFragment() {
        return ScreenItems.SCREEN_ROADS;
    }

    @Override
    public boolean isHomeIndicatorMenu() {
        return false;
    }
}

