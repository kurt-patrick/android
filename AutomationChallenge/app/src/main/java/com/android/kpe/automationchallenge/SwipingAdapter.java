package com.android.kpe.automationchallenge;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by LocalUser on 12/10/2017.
 */

public class SwipingAdapter extends RecyclerView.Adapter<SwipingAdapter.MyViewHolder> {

    private Integer mRowCount = 0;
    private Context mContext = null;

    public SwipingAdapter() {
        mRowCount = 3;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.swiping_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
    }

    public void removeItem(int position) {
        mRowCount -= 1;
        notifyItemRemoved(position);
    }

    @Override
    public int getItemCount() {
        return mRowCount;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private RelativeLayout mViewBackground = null;
        private RelativeLayout mViewForeground = null;

        public MyViewHolder(View itemView) {
            super(itemView);
        }

        public RelativeLayout getViewBackground() {
            if(mViewBackground == null) {
                mViewBackground = (RelativeLayout) itemView.findViewById(R.id.view_background);
            }
            return mViewBackground;
        }

        public RelativeLayout getViewForeground() {
            if(mViewForeground == null) {
                mViewForeground = (RelativeLayout) itemView.findViewById(R.id.swipe_left);
            }
            return mViewForeground;
        }

    }
}
