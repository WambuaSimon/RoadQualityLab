package com.softteco.roadqualitydetector.fragment;

import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CursorAdapter;
import android.widget.Toast;

import com.softteco.roadqualitydetector.R;
import com.softteco.roadqualitydetector.RAApplication;
import com.softteco.roadqualitydetector.adapters.TagsAdapter;
import com.softteco.roadqualitydetector.menu.ScreenItems;
import com.softteco.roadqualitydetector.sqlite.MeasurementsDataHelper;
import com.softteco.roadqualitydetector.sqlite.dao.TagDAO;
import com.softteco.roadqualitydetector.sqlite.model.BaseModel;
import com.softteco.roadqualitydetector.sqlite.model.RoadModel;
import com.softteco.roadqualitydetector.sqlite.model.TagModel;
import com.softteco.roadqualitydetector.ui.SelectProjectDialog;
import com.softteco.roadqualitydetector.view.SwipeMenuListView;

public class TagsListFragment extends SwipeListFragment {

    private final String TAG = TagsListFragment.class.getName();
    public static final String TAGS_LIST_FRAGMENT = "TAGS_LIST_FRAGMENT";

    private TagDAO tagDAO;
    private boolean isMeasurementsScreen;

    @Override
    public int getLayoutFragmentResources() {
        return R.layout.fragment_measurement_list;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkArgs();
        tagDAO = new TagDAO(getActivity());
    }

    private void checkArgs() {
        if (getArguments() != null) {
            isMeasurementsScreen = true;
        }
    }

    @Override
    protected void initUI(final View view) {
        listView = (SwipeMenuListView) view.findViewById(R.id.fr_measurement_list);
        listView.setSelector(R.drawable.black_selectable_bgr);
        listView.setDrawSelectorOnTop(true);
        listView.setBackgroundColor(getResources().getColor(R.color.listview_bg));
        initAdapter();
    }

    @Override
    protected BaseAdapter createAdapter() {
        return new TagsAdapter(getActivity(), tagDAO.
                getTagsByRoadIdCursor(RAApplication.getInstance().getCurrentRoadId()), 0, tagDAO);
    }

    @Override
    protected void onListItemClick(AdapterView<?> parent, View view, int position, long id) {
        final Cursor c = (Cursor) getAdapter().getItem(position);
        TagModel road = tagDAO.cursorToTag(c);
        open(road);
    }

    @Override
    public void onResume() {
        super.onResume();
        refreshTags();
    }

    @Override
    protected int getMoreDlgType() {
        return 3;
    }

    @Override
    public void open(BaseModel model) {
        replaceFragment(TagDetailsFragment.newInstance(model), true);
    }

    @Override
    public String getDeleteItemMsgId(BaseModel model) {
        String msgStr = getAppResouces().getString(R.string.delete_item_prompt_str);
        return msgStr;
    }

    @Override
    protected void edit(BaseModel model) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(NewTagFragment.ARG_TAG_MODEL, model);
        replaceFragment(NewTagFragment.newInstance(bundle), true);
    }

    @Override
    protected void delete(final BaseModel model) {
        new AsyncTask<Void, Void, Cursor>() {
            @Override
            protected Cursor doInBackground(Void[] params) {
                TagModel tagModel = (TagModel) model;
                tagDAO.delete(tagModel);
                final Cursor cursor = tagDAO.
                getTagsByRoadIdCursor(RAApplication.getInstance().getCurrentRoadId());
                return cursor;
            }

            @Override
            protected void onPostExecute(Cursor o) {
                ((CursorAdapter) getAdapter()).swapCursor(o);
            }
        }.execute();
    }

    @Override
    protected void move(BaseModel model) {
        final TagModel tag = (TagModel) model;
        SelectProjectDialog dialog = new SelectProjectDialog(getMainActivity(),
                new SelectProjectDialog.ProjectSelectDialogListener() {
                    @Override
                    public void onProjectSelected(long folderId, long roadId) {
                        moveTagTo(tag.getId(), folderId, roadId);
                    }
                });
        dialog.setCurrentPage(0);
        dialog.showRoadsScreen(true);
        dialog.show();
    }

    private void moveTagTo(long tagId, long roadId, long folderId) {
        MeasurementsDataHelper.getInstance().moveTagTo(tagId, folderId, roadId,
            new MeasurementsDataHelper.MeasurementsDataLoaderListener() {
                @Override
                public void onDataLoaded(Object data) {
                    refreshTags();
                    Toast.makeText(getContext(), getString(R.string.tag_moved_text), Toast.LENGTH_LONG).show();
                }
            });
    }

    public void refreshTags() {
        new AsyncTask<Void, Void, Cursor>() {
            @Override
            protected Cursor doInBackground(Void[] params) {
                final Cursor cursor = tagDAO.getTagsByRoadIdCursor(RAApplication.getInstance().getCurrentRoadId());
                return cursor;
            }
            @Override
            protected void onPostExecute(Cursor o) {
                ((CursorAdapter)getAdapter()).swapCursor(o);
            }
        }.execute();
    }

    @Override
    protected BaseModel getModelItem(int position) {
        final Cursor c = (Cursor) getAdapter().getItem(position);
        return tagDAO.cursorToTag(c);
    }

    @Override
    protected Cursor getSearchCursor(CharSequence searchStr) {
        return null;
    }

    @Override
    public int getMenuFragmentResources() {
        return -1;
    }

    @Override
    public int getTypeFragment() {
        return ScreenItems.SCREEN_TAGS;
    }

    @Override
    public boolean isHomeIndicatorMenu() {
        return !isMeasurementsScreen;
    }
}
