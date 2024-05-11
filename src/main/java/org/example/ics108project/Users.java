package org.example.ics108project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

public class Users {
    static String userName;
    static String userEmail;
    static private String userPassword;
    static Image userImage;

    static ObservableList<Event> userEvents = FXCollections.observableArrayList();

    Users(String name, String email, String password, Image image){
        userName = name;
        userEmail = email;
        userPassword = password;
        userImage = image;
    }

    public void setUserEvents(Event events){
        userEvents.add(events);
    }

    public void setUserName(String name){
        userName = name;
    }

    public void setUserEmail(String email){
        userEmail = email;
    }

    public void setUserPassword(String password){
        userPassword = password;
    }

    public void setUserImage(Image image){
        userImage = image;
    }

    public String getUserName(){
        return userName;
    }

    public String getUserEmail(){
        return userEmail;
    }

    public Image getUserImage(){
        return userImage;
    }

}
