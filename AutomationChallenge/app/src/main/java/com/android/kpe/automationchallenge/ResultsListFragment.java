package com.android.kpe.automationchallenge;

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

import java.io.InvalidObjectException;
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

    private boolean isSuccess() {
        boolean success = false;
        if(mClickedIndexes.size() == mExpectedIndexes.size()) {
            for(int index=0; index<mExpectedIndexes.size(); index++) {
                success = mClickedIndexes.contains(mExpectedIndexes.get(index));
                if(!success) {
                    break;
                }
            }
        }
        return success;
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
            mClickedIndexes.add(index);
            boolean isValid = mExpectedIndexes.contains(index);
            listItem.setColor(isValid);
            if(isValid) {
                if(isSuccess()) {
                    setActualMessage("Success");
                } else {
                    setActualMessage(mClickedIndexes.size() + " of " + mExpectedIndexes + " rows clicked");
                }
            } else {
                setActualMessage("Fail");
            }

        }
    }
}
