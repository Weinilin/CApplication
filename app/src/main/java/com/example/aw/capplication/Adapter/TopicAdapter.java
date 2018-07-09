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

/**
 * Created by Aw on 9/7/2018.
 */

public class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.TopicHolder> {
    private ArrayList<Topic> mTopics;
    private static int UP_VOTE = 0;
    private static int DOWN_VOTE = 1;

    private Activity activity;

    public TopicAdapter(ArrayList<Topic> topics) {
        mTopics = topics;

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
        Topic s = mTopics.get(position);
        holder.bindData(s);
    }

    @Override
    public int getItemCount() {
        return mTopics.size();
    }


    public class TopicHolder extends RecyclerView.ViewHolder {
        private Topic mTopic;
        public ImageView mUpVoteImage;
        public ImageView mDownVoteImage;

        public TextView mUsernameTextView;
        public TextView mDateTextView;
        public TextView mContentTextView;
        public TextView mUpVoteTextView;
        public TextView mDownVoteTextView;

        public TopicHolder(View itemView) {
            super(itemView);
            mUpVoteImage = (ImageView) itemView.findViewById(R.id.up_vote_btn);
            mDownVoteImage = (ImageView) itemView.findViewById(R.id.down_vote_btn);

            mUsernameTextView = (TextView) itemView.findViewById(R.id.username_textview);
            mDateTextView = (TextView) itemView.findViewById(R.id.date_textview);
            mContentTextView = (TextView) itemView.findViewById(R.id.content_textview);
            mUpVoteTextView = (TextView) itemView.findViewById(R.id.up_vote_num_textview);
            mDownVoteTextView = (TextView) itemView.findViewById(R.id.down_vote_num_textview);

        }

        //update the vote in arraylist and then on recycler view
        private void setVoteAndUpdate(TextView textView, int track) {
            if (textView.getText() != null && !textView.getText().toString().equals("")) {
                int numUp = Integer.parseInt(textView.getText().toString()) + 1;
                textView.setText(Integer.toString(numUp));
            } else {
                textView.setText("1");
            }
            if(track == UP_VOTE) {
                mTopics.get(getPosition()).setNumOfUpVote(textView.getText().toString());
            } else {
                mTopics.get(getPosition()).setNumOfDownVote(textView.getText().toString());

            }
            mTopics = sortDescending(mTopics);
            notifyDataSetChanged();
        }



        public void bindData(Topic s) {
            mTopic = s;
            mUsernameTextView.setText(s.getUsername());
            mDateTextView.setText(s.getDate());
            mContentTextView.setText(s.getContent());
            mUpVoteTextView.setText(s.getNumOfUpVote());
            mDownVoteTextView.setText(s.getNumOfDownVote());

        }
    }

    //sort the topics according to descending of up votes
    public ArrayList<Topic> sortDescending(ArrayList<Topic> topics) {
        Collections.sort(topics, new Comparator<Topic>() {
            public int compare(Topic t1, Topic t2) {
                return Integer.parseInt(t1.getNumOfUpVote()) > Integer.parseInt(t2.getNumOfUpVote()) ? -1 : (Integer.parseInt(t1.getNumOfUpVote()) < Integer.parseInt(t2.getNumOfUpVote())) ? 1 : 0;
            }
        });
        return topics;
    }
}

