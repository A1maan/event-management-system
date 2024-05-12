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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class BookingCardController implements Initializable {


    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    ImageView eventImage;
    @FXML
    ImageView cancelButton;
    @FXML
    Button ticketButton;
    @FXML
    Label eventTitle;
    @FXML
    Label eventDate;
    @FXML
    Label eventTickets;


    private ObservableList<Event> bookedEvents = FXCollections.observableArrayList();
    private static ObservableList<Event> events = FXCollections.observableArrayList();
    private Users currUser;
    public void displayEvents(Event event){

        bookedEvents.add(event);
        eventImage.setImage(event.getImage());
        eventTitle.setText(event.getTitle());
        String formattedDate = event.getDate().format(DateTimeFormatter.ofPattern("MMM-dd-yyyy"));
        eventDate.setText(formattedDate);
        eventTickets.setText(event.getCapacity().toString());
    }

    public void setCurrUser(Users loggedUser){
        currUser = loggedUser;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void ticketView(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MyEvents.fxml"));

        root = loader.load();
        MyEventsController myEventsController = loader.getController();

        myEventsController.setData(currUser, events);

        for (Event ev: bookedEvents){
            if (ev.getTitle().equals(eventTitle.getText())){
                myEventsController.ticketView(ev, currUser);
                break;
            }
        }
        //
        stage = (Stage)((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void cancelBooking(MouseEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MyEvents.fxml"));

        root = loader.load();
        MyEventsController myEventsController = loader.getController();

        myEventsController.setData(currUser, events);
        for (Event ev: bookedEvents){
            if (ev.getTitle().equals(eventTitle.getText())){
                myEventsController.cancelView(ev, currUser);
                break;
            }
        }
        //
        stage = (Stage)((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToEventDetailsScene(ActionEvent event) {
    }

    public void saveData(ObservableList<Event> events) {
        this.events = events;
    }
}
