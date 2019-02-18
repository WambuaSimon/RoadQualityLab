package com.softteco.roadqualitydetector.adapters;

import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.softteco.roadqualitydetector.R;
import com.softteco.roadqualitydetector.RAApplication;
import com.softteco.roadqualitydetector.sqlite.MeasurementsDataHelper;
import com.softteco.roadqualitydetector.sqlite.dao.TagDAO;
import com.softteco.roadqualitydetector.sqlite.model.MeasurementItemType;
import com.softteco.roadqualitydetector.sqlite.model.TagModel;
import com.softteco.roadqualitydetector.util.FileUtils;
import com.softteco.roadqualitydetector.view.CircleImageView;

public class TagsAdapter extends CursorAdapter {

    private final String TAG = TagsAdapter.class.getName();

    private TagDAO tagDAO;
    private Context context;

    public TagsAdapter(Context context, Cursor c, int flags, TagDAO dao) {
        super(context, c, flags);
        this.tagDAO = dao;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.tag_list_item, parent, false);
    }

    @Override
    public void bindView(View view, final Context context, final Cursor cursor) {
        final TagModel model = tagDAO.cursorToTag(cursor);
        final TextView titleText = (TextView) view.findViewById(R.id.tag_item_title);
        final TextView detailsText = (TextView) view.findViewById(R.id.tag_item_details);
        final TextView iriText = (TextView) view.findViewById(R.id.tag_item_iri);
        final ImageView imageView = (ImageView) view.findViewById(R.id.tag_item_is_uploaded);
        final CircleImageView roadConditionIcon = (CircleImageView) view.findViewById(R.id.tag_list_item_circle);
        //String titleStr = MeasurementsDataHelper.getInstance().getItemTitleStr(model);
        String titleStr = FileUtils.getExportItemName(context, model.getId(), model.getDate(), MeasurementItemType.TAG);
        String notesStr = model.getNotes();
        String iriStr = String.valueOf(model.getIri());
        titleText.setText(titleStr);
        if (TextUtils.isEmpty(notesStr)) {
            detailsText.setVisibility(View.GONE);
        } else {
            detailsText.setVisibility(View.VISIBLE);
            detailsText.setText(notesStr);
        }
        iriText.setText(iriStr);
        imageView.setVisibility(model.isUploaded() ? View.VISIBLE : View.INVISIBLE);
        roadConditionIcon.setVisibility(View.GONE);
        if (model.getRoadCondition() != null &&
            !TagModel.RoadCondition.NONE.equals(model.getRoadCondition())) {
            roadConditionIcon.setVisibility(View.VISIBLE);
            int color = model.getRoadConditionColor(RAApplication.getInstance());
            roadConditionIcon.setColorBackground(color);
        }
    }

    private void updateTag(TagModel tagModel) {
        new AsyncTask<TagModel, Void, Void>() {
            @Override
            protected Void doInBackground(TagModel... params) {
                tagDAO.updateItem(params[0]);
                return null;
            }
        }.execute(tagModel);
    }

    @Override
    public void changeCursor(Cursor cursor) {
        super.changeCursor(cursor);
    }

}