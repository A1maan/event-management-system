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
import java.util.ResourceBundle;

public class EventCardController implements Initializable {
    ObservableList<Event> events = FXCollections.observableArrayList();
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
        events.add(event);
        InputStream stream = null;
        try {
            stream = new FileInputStream("/Users/almaan/Library/CloudStorage/OneDrive-KFUPM/Class Notes/Term-232/ICS108/ICS108-Project/src/main/resources/org/example/ics108project/project-images/defualtImage.jpg");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        Image image = new Image(stream);
        eventImage.setImage(image);
        eventTitle.setText(event.getTitle());
        eventDate.setText(event.getDate().toString());
        eventTickets.setText(event.getCapacity().toString());

    }

    public void switchToEventDetailsScene(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("EventDetails.fxml"));

        // the next code block to pass the chosen event to eventDetails
        root = loader.load();
        EventDetailsCont eventDetailsCont = loader.getController();
        for (Event ev: events){
            if (ev.getTitle().equals(eventTitle.getText())){
                eventDetailsCont.displayData(ev);
                break;
            }
        }
        eventDetailsCont.saveData(events);

        //
        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
