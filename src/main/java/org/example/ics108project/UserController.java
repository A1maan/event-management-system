package org.example.ics108project;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UserController implements Initializable  {

    ObservableList<Event> events = FXCollections.observableArrayList();

    public void displayData(ObservableList<Event> event){
        events.addAll(event);
        System.out.println(events);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
