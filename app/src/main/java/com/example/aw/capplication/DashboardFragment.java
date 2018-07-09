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
import android.widget.TextView;

import com.example.aw.capplication.Adapter.TopicAdapter;
import com.example.aw.capplication.Dialog.AddTopicFragment;
import com.example.aw.capplication.Dialog.SignInDialogFragment;
import com.example.aw.capplication.Model.Topic;

import org.w3c.dom.Text;

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
    private TextView no_topic_textview;
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
        toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
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
        no_topic_textview = view.findViewById(R.id.textview_empty);
        mTopicRecyclerView = (RecyclerView) view.findViewById(R.id.topics_recycler_view);
        for (int i = 0; i < 20; i++) {
            mTopics.add(new Topic());
        }
        //display instruction when no topics added
        if (mTopics.size() > 0) {
            no_topic_textview.setVisibility(View.GONE);
            mTopicRecyclerView.setVisibility(View.VISIBLE);
            //always only display top 20 even after user add addition
            setTop20();
            updateUI();
        } else {
            no_topic_textview.setVisibility(View.VISIBLE);
            mTopicRecyclerView.setVisibility(View.GONE);
        }

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
        mAdapter = new TopicAdapter(mTopics, top20Topics);
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

                        //display username on toolbar
                        toolbar.setTitle("Hello " + username + ", ");
                        //inital new topic
                        Topic newTopic = new Topic(username, content, "0", nowDate);
                        mTopics.add(newTopic);

                        if (no_topic_textview.getVisibility() == View.VISIBLE) {
                            mAdapter = new TopicAdapter(top20Topics);
                            no_topic_textview.setVisibility(View.GONE);
                            mTopicRecyclerView.setVisibility(View.VISIBLE);
                        }
                        mTopics = mAdapter.sortDescending(mTopics);

                        //always only display top 20 even after user add new topic
                        setTop20();
                        updateUI();

                    }
                }

                break;
        }
    }

}
