package org.example.ics108project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class EventCardController implements Initializable {
    private static ObservableList<Event> events = FXCollections.observableArrayList();
    private ObservableList<Event> users = FXCollections.observableArrayList();

    private Users currUser;
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    ImageView eventImage;
    @FXML
    Label eventDate;
    @FXML
    Label eventTickets;
    @FXML
    Label eventTitle;

    @Override
    public void initialize(URL url, ResourceBundle rb){
    }

    public void setData(Event event){
        if (!events.contains(event)){
            events.add(event);
        }

        //currUser = loggedUser;
        eventImage.setImage(event.getImage());
        eventTitle.setText(event.getTitle());
        String formattedDate = event.getDate().format(DateTimeFormatter.ofPattern("MMM-dd-yyyy"));
        eventDate.setText(formattedDate);
        eventTickets.setText(event.getCapacity().toString());
    }

    public void switchToEventDetailsScene(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("EventDetails.fxml"));

        root = loader.load();
        EventDetailsCont eventDetailsCont = loader.getController();
        eventDetailsCont.saveData(events);
        for (Event ev: events){
            if (ev.getTitle().equals(eventTitle.getText())){
                eventDetailsCont.displayData(ev, currUser);
                break;
            }
        }
        //
        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


}
