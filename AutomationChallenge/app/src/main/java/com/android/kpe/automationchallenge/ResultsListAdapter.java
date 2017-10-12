package com.android.kpe.automationchallenge;

import android.content.Context;
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
    public ResultsListAdapter(ArrayList<ResultListItem> items) {
        mItems = items;
    }

    @Override
    public ListItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View itemView = LayoutInflater.from(context).inflate(R.layout.results_list_item, parent, false);
        return new ListItemViewHolder(itemView);
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

        public ListItemViewHolder(View itemView) {
            super(itemView);
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
            ResultListItem selectedBook = mItems.get(position);
        }
    }

}
