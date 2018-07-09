package com.example.aw.capplication.Model;

/**
 * Created by Aw on 9/7/2018.
 */

public class Topic {
    private String username = "Brenda", content = "topic of the day", date = "08/09/2017", numOfVote = "0";

    public Topic() {

    }

    public Topic(String username, String content,  String numOfVote, String nowDate) {
        setUsername(username);
        setContent(content);
        setNumOfVote(numOfVote);
        setDate(nowDate);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNumOfVote() {
        return numOfVote;
    }

    public void setNumOfVote(String numOfUpVote) {
        this.numOfVote = numOfUpVote;
    }




}


