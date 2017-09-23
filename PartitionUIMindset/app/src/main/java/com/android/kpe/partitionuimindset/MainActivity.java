package com.android.kpe.partitionuimindset;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity implements OnCourseChangedListener {

    public final static String LOG_TAG = "AndroidMindset";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onCourseChanged(int courseIndex) {
        android.app.FragmentManager fragmentManager = getFragmentManager();
        DescFragment descFragment = (DescFragment) fragmentManager.findFragmentById(R.id.frgDescription);
        descFragment.setCourse(courseIndex);
    }
}
