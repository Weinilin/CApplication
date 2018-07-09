package com.example.aw.capplication.Model;

/**
 * Created by Aw on 9/7/2018.
 */

public class Topic {
    private String username = "Brenda", content = "topic of the day", date = "08/09/2017", numOfUpVote = "100", numOfDownVote = "10";

    public Topic() {

    }

    public Topic(String username, String content, String numOfDownVote, String numOfUpVote, String nowDate) {
        setUsername(username);
        setContent(content);
        setNumOfDownVote(numOfDownVote);
        setNumOfUpVote(numOfUpVote);
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

    public String getNumOfUpVote() {
        return numOfUpVote;
    }

    public void setNumOfUpVote(String numOfUpVote) {
        this.numOfUpVote = numOfUpVote;
    }

    public String getNumOfDownVote() {
        return numOfDownVote;
    }

    public void setNumOfDownVote(String numOfDownVote) {
        this.numOfDownVote = numOfDownVote;
    }


}


