package com.android.kpe.automationchallenge;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by LocalUser on 12/10/2017.
 */

public class ResultsListAdapter extends RecyclerView.Adapter<ResultsListAdapter.ListItemViewHolder> {

    private ArrayList<ResultListItem> mItems = null;
    private ResultsListFragment mParentFragment = null;

    public ResultsListAdapter(ResultsListFragment parent, ArrayList<ResultListItem> items) {
        mItems = items;
        mParentFragment = parent;
    }

    @Override
    public ListItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View itemView = LayoutInflater.from(context).inflate(R.layout.results_list_item, parent, false);
        return new ListItemViewHolder(mParentFragment, itemView);
    }

    @Override
    public void onBindViewHolder(ListItemViewHolder holder, int position) {
        holder.bind(mItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public class ListItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ResultsListFragment mParent;

        public ListItemViewHolder(ResultsListFragment parent, View itemView) {
            super(itemView);
            mParent = parent;
            if(mParent == null) {
                throw new NullPointerException("parent cannot be null");
            }
            itemView.setOnClickListener(this);
        }

        public void bind(ResultListItem item) {
            setText(R.id.txt1, item.one);
            setText(R.id.txt2, item.two);
            setText(R.id.txt3, item.three);
            setText(R.id.txt4, item.four);
        }

        private void setText(int id, String text) {
            TextView textView = (TextView) itemView.findViewById(id);
            if(textView != null) {
                textView.setText(text == null ? "" : text);
            }
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            ResultListItem selectedItem = mItems.get(position);
            mParent.onRowClick(position, this);
        }

        public void setColor(boolean isCorrect) {
            if(isCorrect) {
                itemView.setBackgroundColor(Color.GREEN);
            } else {
                itemView.setBackgroundColor(Color.RED);
            }
        }

        public void resetColor() {
            itemView.setBackgroundColor(Color.GREEN);
        }

    }

}
