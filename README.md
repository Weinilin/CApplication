Things to take note:
For unit testing to work, you have to do this following
On Android Studio.
1.	Edit Configurations
2.	In Junit, you have to change the working directory to $MODULE_DIR$.

hows and whys of your implementation :
1. Recyclerview for the display of top 20 list of topics as the data is frequently change when user clicked on the up/down votes 
and it would only load the new data when scrolling.
2. Class extends application for datastructure of topics and generic methods like sort in descending order and display of top 20 topics. 
As this is to keep a common Arraylist of topics able to access throughout the whole application and reusability of some generic methods. 


Assumptions:
1. Any username and password could be used to login.
2. No logout function.
3. Only top 20 votes of topic shown in the screen.
4. Down/up vote refresh the list of topic:
      3.1. if user lower the vote of topic of index number 20 until it is lower than topic of index number 21, num 20 topic would 
           replace with num 21 topic and show on the view.
