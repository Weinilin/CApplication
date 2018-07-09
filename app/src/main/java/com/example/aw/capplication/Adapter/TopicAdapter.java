package com.example.aw.capplication.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aw.capplication.Model.Topic;
import com.example.aw.capplication.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static com.example.aw.capplication.Model.TopicList.top20Topics;
import static com.example.aw.capplication.Model.TopicList.topicsFullList;
import static com.example.aw.capplication.Model.TopicList.updateTopicList;

/**
 * Created by Aw on 9/7/2018.
 */

public class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.TopicHolder> {



    public TopicAdapter() {

    }


    @Override
    public TopicAdapter.TopicHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.list_item, parent, false);
        return new TopicHolder(view);
    }

    @Override
    public void onBindViewHolder(TopicAdapter.TopicHolder holder, int position) {
        Topic s = top20Topics.get(position);
        holder.bindData(s);
    }

    @Override
    public int getItemCount() {
        return top20Topics.size();
    }


    public class TopicHolder extends RecyclerView.ViewHolder {
        private Topic mTopic;
        public ImageView mUpVoteImage;
        public ImageView mDownVoteImage;

        public TextView mUsernameTextView;
        public TextView mDateTextView;
        public TextView mContentTextView;
        public TextView mTotalVoteTextView;

        public TopicHolder(View itemView) {
            super(itemView);
            mUpVoteImage = (ImageView) itemView.findViewById(R.id.up_vote_btn);
            mDownVoteImage = (ImageView) itemView.findViewById(R.id.down_vote_btn);

            mUsernameTextView = (TextView) itemView.findViewById(R.id.username_textview);
            mDateTextView = (TextView) itemView.findViewById(R.id.date_textview);
            mContentTextView = (TextView) itemView.findViewById(R.id.content_textview);
            mTotalVoteTextView = (TextView) itemView.findViewById(R.id.total_vote_num_textview);

            //on click listerner for up and down
            mUpVoteImage.setOnClickListener(onClickListener);
            mDownVoteImage.setOnClickListener(onClickListener);
        }

        private View.OnClickListener onClickListener = new View.OnClickListener() {

            @Override
            public void onClick(final View v) {
                switch (v.getId()) {
                    case R.id.up_vote_btn:

                        setVoteAndUpdate(1);
                        break;
                    case R.id.down_vote_btn:
                        setVoteAndUpdate(-1);

                        break;
                }

            }
        };

        //update the vote in arraylist and then on recycler view
        private void setVoteAndUpdate(int num) {

            int numUp = Integer.parseInt(mTotalVoteTextView.getText().toString()) + num;
            mTotalVoteTextView.setText(Integer.toString(numUp));

            topicsFullList.get(getPosition()).setNumOfVote(mTotalVoteTextView.getText().toString());

            updateTopicList();
            notifyDataSetChanged();
        }


        public void bindData(Topic s) {
            mTopic = s;
            mUsernameTextView.setText(s.getUsername());
            mDateTextView.setText(s.getDate());
            mContentTextView.setText(s.getContent());
            mTotalVoteTextView.setText(s.getNumOfVote());

        }
    }



}

