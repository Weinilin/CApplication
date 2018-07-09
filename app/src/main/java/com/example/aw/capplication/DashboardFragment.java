package com.example.aw.capplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.aw.capplication.Adapter.TopicAdapter;
import com.example.aw.capplication.Dialog.AddTopicFragment;
import com.example.aw.capplication.Dialog.SignInDialogFragment;
import com.example.aw.capplication.Model.Topic;
import com.example.aw.capplication.Model.TopicList;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static com.example.aw.capplication.Model.TopicList.updateTopicList;

/**
 * Created by Aw on 9/7/2018.
 */

public class DashboardFragment extends Fragment {
    private String username = "";
    private static final int DIALOG_FRAGMENT = 1;

    private Toolbar toolbar;
    private RecyclerView mTopicRecyclerView;
    private TextView no_topic_textview;

    private static final DateFormat sdf = new SimpleDateFormat("dd MMM");
    private TopicAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        return inflater.inflate(R.layout.fragment_dashboard, parent, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        //for adding topic by user
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(onClickListener);

        no_topic_textview = view.findViewById(R.id.textview_empty);
        mTopicRecyclerView = (RecyclerView) view.findViewById(R.id.topics_recycler_view);
        InitilizeTopicList();
        //display instruction when no topics added
        if (TopicList.topicsFullList.size() > 0) {
            no_topic_textview.setVisibility(View.GONE);
            mTopicRecyclerView.setVisibility(View.VISIBLE);
            //always only display top 20 even after user add addition
            updateTopicList();
            updateUI();
        } else {
            no_topic_textview.setVisibility(View.VISIBLE);
            mTopicRecyclerView.setVisibility(View.GONE);
        }

    }

    //when click on "+" btn
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(final View v) {
            switch (v.getId()) {
                case R.id.fab:
                    //go sigin page if no user
                    if (username.equals("")) {
                        SignInDialogFragment signInDialogFragment = new SignInDialogFragment();
                        signInDialogFragment.setTargetFragment(DashboardFragment.this, DIALOG_FRAGMENT);
                        signInDialogFragment.show(getFragmentManager(), "SignIn Dialog");

                    } else {
                        //go straight to add topic dialog
                        AddTopicFragment addTopicFragment = new AddTopicFragment();
                        addTopicFragment.setTargetFragment(DashboardFragment.this, DIALOG_FRAGMENT);

                        addTopicFragment.show(getFragmentManager(), "AddTopic Dialog");
                    }
                    break;
            }

        }
    };

    private void InitilizeTopicList() {
        for (int i = 0; i < 20; i++) {
            TopicList.topicsFullList.add(new Topic());
        }
    }


    private void updateUI() {
        mAdapter = new TopicAdapter();
        mTopicRecyclerView.setAdapter(mAdapter);
        // Set layout manager to position the items
        mTopicRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case DIALOG_FRAGMENT:
                if (resultCode == Activity.RESULT_OK) {
                    // After Ok code.
                    Bundle bundle = data.getExtras();
                    if (bundle != null) {
                        //get the new Topic keyed by user
                        if (username.equals(""))
                            username = bundle.getString(getResources().getString(R.string.USERNAME_TEXT));
                        Topic newTopic = getNewTopic(bundle);
                        TopicList.topicsFullList.add(newTopic);

                        if (no_topic_textview.getVisibility() == View.VISIBLE) {
                            mAdapter = new TopicAdapter();
                            no_topic_textview.setVisibility(View.GONE);
                            mTopicRecyclerView.setVisibility(View.VISIBLE);
                        }
                        updateTopicList();
                        updateUI();

                    }
                }

                break;
        }
    }

    //set new topic and return
    @NonNull
    private Topic getNewTopic(Bundle bundle) {
        String content = bundle.getString(getResources().getString(R.string.TOPIC_CONTENT_TEXT));
        Date date = new Date();
        String nowDate = sdf.format(date);

        //display username on toolbar
        toolbar.setTitle("Hello " + username + ", ");
        //inital new topic
        return new Topic(username, content, "0", nowDate);
    }

}
