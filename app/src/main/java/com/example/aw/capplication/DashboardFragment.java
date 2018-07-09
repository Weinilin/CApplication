package com.example.aw.capplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.aw.capplication.Adapter.TopicAdapter;
import com.example.aw.capplication.Dialog.AddTopicFragment;
import com.example.aw.capplication.Dialog.SignInDialogFragment;
import com.example.aw.capplication.Model.Topic;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Aw on 9/7/2018.
 */

public class DashboardFragment extends Fragment {
    private String username = "";
    private static final int DIALOG_FRAGMENT = 1;

    private Toolbar toolbar;
    private RecyclerView mTopicRecyclerView;
    private ArrayList<Topic> mTopics = new ArrayList<>();
    private ArrayList<Topic> top20Topics = new ArrayList<>();
    private static final DateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");
    private TopicAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        return inflater.inflate(R.layout.fragment_dashboard, parent, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        //for adding topic by user
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View view) {
                                       //go sigin page if no user
                                       if (username.equals("")) {

                                           SignInDialogFragment signInDialogFragment = new SignInDialogFragment();
                                           signInDialogFragment.setTargetFragment(DashboardFragment.this, DIALOG_FRAGMENT);
                                           signInDialogFragment.show(getFragmentManager(), "SignIn Dialog");

                                       } else {

                                           AddTopicFragment addTopicFragment = new AddTopicFragment();
                                           addTopicFragment.setTargetFragment(DashboardFragment.this, DIALOG_FRAGMENT);

                                           addTopicFragment.show(getFragmentManager(), "AddTopic Dialog");
                                       }
                                   }
                               }
        );


        //add in 20 topics
        for (int i = 0; i < 20; i++) {
            Topic topic = new Topic();
            topic.setNumOfUpVote("0");
            topic.setNumOfDownVote("0");
            topic.setUsername("Brenda" + Integer.toString(i));
            topic.setContent("Topic of the day \n" + Integer.toString(i));

            mTopics.add(topic);

        }

        //always only display top 20 even after user add addition
        setTop20();

        mTopicRecyclerView = (RecyclerView) view.findViewById(R.id.topics_recycler_view);

        updateUI();
    }

    private void setTop20() {
        top20Topics.clear();
        for (int i = 0; i < mTopics.size(); i++) {
            if (i == 20) {
                break;
            }
            top20Topics.add(mTopics.get(i));

        }
    }

    private void updateUI() {
        mAdapter = new TopicAdapter(top20Topics);
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
                        String content = bundle.getString(getResources().getString(R.string.TOPIC_CONTENT_TEXT));
                        Date date = new Date();
                        String nowDate = sdf.format(date);


                        toolbar.setTitle("Hello " + username);
                        Topic newTopic = new Topic(username, content, "0", "0", nowDate);

                        mTopics.add(newTopic);
                        top20Topics = mAdapter.sortDescending(top20Topics);

                        setTop20();
                        updateUI();

                    }
                }

                break;
        }
    }

}
