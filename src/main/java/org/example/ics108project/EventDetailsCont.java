package org.example.ics108project;

import javafx.beans.property.ObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class EventDetailsCont implements Initializable {

    ObservableList<Event> events = FXCollections.observableArrayList();

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    Label titleText;
    @FXML
    Label descriptionText;
    @FXML
    Label dateText;
    @FXML
    Label timeText;
    @FXML
    Label locationText;
    @FXML
    Label categoryText;
    @FXML
    Label ticketsText;
    @FXML
    Button bookingButton;

    public void saveData(ObservableList<Event> passedEvents){
        events.addAll(passedEvents);
    }

    public void displayData(Event event){
        titleText.setText(event.getTitle());
        descriptionText.setText(event.getDescription());

        String formattedDate = event.getDate().format(DateTimeFormatter.ofPattern("MMM-dd-yyyy"));
        dateText.setText(formattedDate);

        timeText.setText(event.getTime());
        locationText.setText(event.getLocation());
        ticketsText.setText("Tickets Left: " + event.getCapacity());
        categoryText.setText(event.getCategory());
    }

    public void switchToAdminScene(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Admin.fxml"));

        root = loader.load();
        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void switchToUserScene(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("User.fxml"));

        // the next code block to pass the chosen event to eventDetails
        root = loader.load();
        UserController userController = loader.getController();
        userController.displayData(events);
        //
        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
