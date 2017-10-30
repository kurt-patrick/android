package com.android.kpe.automationchallenge;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class ResultsListFragment extends Fragment implements  OnResultsListItemClickListener {

    private RecyclerView mRecyclerView = null;
    private TextView mTxtExpected = null;
    private TextView mTxtActual = null;
    private ArrayList<Integer> mExpectedIndexes = new ArrayList<Integer>();
    private ArrayList<Integer> mClickedIndexes = new ArrayList<Integer>();

    public ResultsListFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_results_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getRecyclerView().setVisibility(View.VISIBLE);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getView().getContext(), LinearLayoutManager.VERTICAL, false);
        getRecyclerView().setLayoutManager(layoutManager);

        // Generate random row data
        ArrayList<ResultListItem> items = ResultListItem.getRandomList(6);

        // Select a random index
        int randomIndex = RandomHelper.max(items.size() - 1);

        // Find all rows matching randomIndex
        mClickedIndexes.clear();
        mExpectedIndexes.clear();
        for(int index=0; index< items.size(); index++) {
            if(items.get(index).isEqual(items.get(randomIndex))) {
                mExpectedIndexes.add(index);
            }
        }

        // make sure indexes have been setup
        if(mExpectedIndexes.size() == 0) {
            throw new IllegalArgumentException("Expected indexes must contain at least one int");
        }

        // set expected string
        ResultListItem item = items.get(randomIndex);
        getTxtExpected().setText(item.one + " " + item.two + " " + item.three + " " + item.four);
        setActualMessage("0 rows clicked");

        ResultsListAdapter adapter = new ResultsListAdapter(this, items);
        getRecyclerView().setAdapter(adapter);
        getRecyclerView().setHasFixedSize(true);

        DividerItemDecoration horizontalDecoration = new DividerItemDecoration(getRecyclerView().getContext(),
                DividerItemDecoration.VERTICAL);
        Drawable horizontalDivider = ContextCompat.getDrawable(getActivity(), R.drawable.horizontal_divider);
        horizontalDecoration.setDrawable(horizontalDivider);
        getRecyclerView().addItemDecoration(horizontalDecoration);
    }

    private RecyclerView getRecyclerView() {
        if(mRecyclerView == null) {
            mRecyclerView = ((RecyclerView) getView().findViewById(R.id.recView));
        }
        return mRecyclerView;
    }

    private TextView getTxtExpected() {
        if(mTxtExpected == null) {
            mTxtExpected = ((TextView) getView().findViewById(R.id.txtExpected));
        }
        return mTxtExpected;
    }

    private void setActualMessage(String text) {
        TextView textView = getTxtActual();
        textView.setText(text);
    }

    private int getInvalidClickCount() {
        int retVal = 0;
        for(int index=0; index<mClickedIndexes.size(); index++) {
            if (!mExpectedIndexes.contains(mClickedIndexes.get(index))) {
                retVal += 1;
            };
        }
        return retVal;
    }

    private int getValidClickCount() {
        int retVal = 0;
        for(int index=0; index<mExpectedIndexes.size(); index++) {
            if (mClickedIndexes.contains(mExpectedIndexes.get(index))) {
                retVal += 1;
            };
        }
        return retVal;
    }

    private TextView getTxtActual() {
        if(mTxtActual == null) {
            mTxtActual = ((TextView) getView().findViewById(R.id.txtActual));
        }
        return mTxtActual;
    }

    @Override
    public void onRowClick(int index, ResultsListAdapter.ListItemViewHolder listItem) {
        if(!mClickedIndexes.contains(index)) {

            // keep track of clicked indexes
            mClickedIndexes.add(index);

            // determine if this is a row that should be clicked
            boolean isValidIndex = mExpectedIndexes.contains(index);

            // set the rows colour to red or green
            listItem.setColor(isValidIndex);

            //  && mClickedIndexes.size() <= mExpectedIndexes.size()
            int validClickCount = getValidClickCount();
            int invalidClickCount = getInvalidClickCount();

            if(invalidClickCount > 0) {
                setActualMessage("Fail");
            } else {
                if(validClickCount == mExpectedIndexes.size()) {
                    setActualMessage("Success");
                } else {
                    setActualMessage(mClickedIndexes.size() + " of " + mExpectedIndexes.size() + " rows clicked");
                }
            }

        }
    }
}
