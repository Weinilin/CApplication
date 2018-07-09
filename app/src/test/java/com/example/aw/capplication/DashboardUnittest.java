package com.example.aw.capplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.aw.capplication.Adapter.TopicAdapter;
import com.example.aw.capplication.Model.Topic;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.ArrayList;

import static com.example.aw.capplication.Model.TopicList.top20Topics;
import static com.example.aw.capplication.Model.TopicList.topicsFullList;
import static com.example.aw.capplication.Model.TopicList.updateTopicList;
import static org.junit.Assert.assertEquals;

/**
 * Created by Aw on 9/7/2018.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class DashboardUnittest {

    @Before
    public void setUp() throws Exception {

    }


    @Test
    public void max_char_is_correct() throws Exception {
        //add more that max char 255
        ArrayList<Topic> topics = new ArrayList<>();
        Topic topic = new Topic();

        for (int i = 0; i < 270; i++) {
            topic.setContent(topic.getContent() + Integer.toString(i));
        }

        LayoutInflater inflater = (LayoutInflater) RuntimeEnvironment.application.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //We have a layout especially for the items in our recycler view. We will see it in a moment.
        View listItemView = inflater.inflate(R.layout.list_item, null, false);
        TextView textView = listItemView.findViewById(R.id.content_textview);
        textView.setText(topic.getContent());
        assertEquals(textView.getText().toString().length(), 255);

    }

    @Test
    public void sorting_is_correct() throws Exception {
        //stub for topics
        int n = 20;
        for (int i = 0; i < 20; i++) {
            Topic topic = new Topic();
            if (i == 14) {
                //set index 14 to have max up votes
                topic.setNumOfVote("1000");
                topic.setUsername("Brenda14max");


            } else {
                topic.setNumOfVote(Integer.toString(n));
                topic.setUsername("Brenda" + Integer.toString(n--));

            }
            topicsFullList.add(topic);


        }


        //Acess the sorting function
        updateTopicList();
        n = 20;
        //test the sequence of display im top 20 list
        for (int i = 0; i < 20; i++) {

            if (i == 0) {
                //check if prev index 14 (with the highest up votes) is now index 0
                assertEquals(top20Topics.get(i).getNumOfVote(), "1000");

                assertEquals(top20Topics.get(i).getUsername(), "Brenda14max");

            } else {
                //check if the sequence is right after sorting it
                assertEquals(top20Topics.get(i).getNumOfVote(), Integer.toString(n));

                assertEquals(top20Topics.get(i).getUsername(), "Brenda" + Integer.toString(n));
                n--;
            }

        }


    }


}



