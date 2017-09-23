package com.android.kpe.partitionuimindset;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by LocalUser on 20/09/2017.
 */

public class DescFragment extends Fragment {

    String[] mCourseDescriptions = null;
    TextView mCourseDescTextView = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mCourseDescriptions = getResources().getStringArray(R.array.course_descriptions);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View theView = inflater.inflate(R.layout.fragment_description, container, false);

        mCourseDescTextView = (TextView) theView.findViewById(R.id.courseDescription);

        return theView;
    }

    public void setCourse(int index)
    {
        mCourseDescTextView.setText(mCourseDescriptions[index]);
    }
}
