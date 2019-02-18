package com.softteco.roadqualitydetector.fragment;

import com.google.android.gms.maps.model.Marker;
import com.softteco.roadqualitydetector.menu.ScreenItems;
import com.softteco.roadqualitydetector.sqlite.model.BumpModel;
import com.softteco.roadqualitydetector.sqlite.model.MeasurementItem;
import com.softteco.roadqualitydetector.sqlite.model.RoadModel;
import com.softteco.roadqualitydetector.sqlite.MeasurementsDataHelper;
import com.softteco.roadqualitydetector.sqlite.model.TagModel;

import java.util.List;

public class MeasurementMapFragment extends PopupMeasurementMapFragment {

    private RoadModel roadModel;

    @Override
    protected void checkArgs() {
        if (getArguments() != null && getArguments().getSerializable(MeasurementListFragment.ARG_MEASUREMENT_LIST) != null) {
            roadModel = (RoadModel) getArguments().getSerializable(MeasurementListFragment.ARG_MEASUREMENT_LIST);
        }
    }

    @Override
    protected void updateTitle() {
        setTitle(roadModel.getName());
    }

    @Override
    protected void initMapData() {
        MeasurementsDataHelper.getInstance().
                getData(roadModel, new MeasurementsDataHelper.MeasurementsDataLoaderListener<List<MeasurementItem>>() {
                    @Override
                    public void onDataLoaded(List<MeasurementItem> items) {
                        onItemsReady(items, true);
                    }
                });
    }

    @Override
    public int getTypeFragment() {
        return ScreenItems.SCREEN_MEASUREMENT;
    }

    @Override
    public boolean isHomeIndicatorMenu() {
        return false;
    }
}

