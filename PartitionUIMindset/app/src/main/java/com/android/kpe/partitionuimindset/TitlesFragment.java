package com.android.kpe.partitionuimindset;

import android.app.ListFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by LocalUser on 20/09/2017.
 */

public class TitlesFragment extends ListFragment {

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        ((OnCourseChangedListener) getActivity() ).onCourseChanged(position);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        String[] titles = getResources().getStringArray(R.array.course_titles);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getActivity(), android.R.layout.simple_list_item_1, titles);

        setListAdapter(adapter);

    }
}
