package com.example.aw.capplication.Model;

import android.app.Application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Aw on 9/7/2018.
 */

public class TopicList extends Application {
    public static ArrayList<Topic> topicsFullList = new ArrayList<>();
    public static ArrayList<Topic> top20Topics = new ArrayList<>();

    public TopicList() {

    }

    public ArrayList<Topic> getTopicsFullList() {
        return topicsFullList;
    }

    public void setTopicsFullList(ArrayList<Topic> topicsFullList) {
        this.topicsFullList = topicsFullList;
    }

    public ArrayList<Topic> getTop20Topics() {
        return top20Topics;
    }

    public void setTop20Topics(ArrayList<Topic> top20Topics) {
        this.top20Topics = top20Topics;
    }

    public static void updateTopicList() {
        sortDescending();
        getTop20();
    }

    //sort the topics according to descending of up votes
    public static void sortDescending() {
        Collections.sort(topicsFullList, new Comparator<Topic>() {
            public int compare(Topic t1, Topic t2) {
                return Integer.parseInt(t1.getNumOfVote()) > Integer.parseInt(t2.getNumOfVote()) ? -1 : (Integer.parseInt(t1.getNumOfVote()) < Integer.parseInt(t2.getNumOfVote())) ? 1 : 0;
            }
        });

    }

    public static ArrayList<Topic> getTop20() {
        top20Topics.clear();
        for (int i = 0; i < topicsFullList.size(); i++) {
            if (i == 20) {
                break;
            }
            top20Topics.add(topicsFullList.get(i));

        }
        return top20Topics;
    }

}
