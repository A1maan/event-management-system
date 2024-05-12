package org.example.ics108project;

import javafx.beans.property.ObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class EventDetailsCont implements Initializable {

    ObservableList<Event> events = FXCollections.observableArrayList();
    Event currEvent;

    static ObservableList<Users> users = FXCollections.observableArrayList();

    private Users currUser;
    boolean alrdyConfirmed = true;

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
    @FXML
    AnchorPane confirmAnchor;
    @FXML
    Rectangle coverScreen;
    @FXML
    ImageView cancelBooking;
    @FXML
    Button confirmBooking;
    @FXML
    TextField fullName;
    @FXML
    TextField email;
    @FXML
    Label errorMessage;
    @FXML
    ImageView ticket;
    @FXML
    Label ticketName;
    @FXML
    Label ticketEvent;
    @FXML
    Label l1;
    @FXML
    Label l2;
    @FXML
    ImageView eventDetailsImage;
    @FXML
    ImageView userImage;
    @FXML
    Button loginButton;

    public void saveData(ObservableList<Event> passedEvents){
        events.addAll(passedEvents);
    }

    public void displayData(Event event, Users loggedUser){

        if (loggedUser != null){
            currUser = loggedUser;
            loginButton.setText(currUser.getUserName());
            userImage.setImage(currUser.getUserImage());
        }

        currEvent = event;
        titleText.setText(event.getTitle());
        descriptionText.setText(event.getDescription());

        String formattedDate = event.getDate().format(DateTimeFormatter.ofPattern("MMM-dd-yyyy"));
        dateText.setText(formattedDate);

        timeText.setText(event.getTime());
        locationText.setText(event.getLocation());
        ticketsText.setText("Tickets Left: " + event.getCapacity());
        categoryText.setText(event.getCategory());
        eventDetailsImage.setImage(event.getImage());
    }

    // confirm booking and sending ticket
    public void openConfirmation(ActionEvent event){
        if (currEvent.getCapacity() > 0){
            coverScreen.setVisible(true);
            confirmAnchor.setVisible(true);
        }else{
            ticketsText.setText("No Tickets Available");
            ticketsText.setStyle("-fx-background-color: Red");
        }


    }

    public void cancelConfirmation() throws IOException{
        coverScreen.setVisible(false);
        confirmAnchor.setVisible(false);
    }

    public void sendTicket() throws IOException{
        String userName = fullName.getText();
        String userEmail = email.getText();

        if (userName.isEmpty() || userEmail.isEmpty() || !userEmail.contains("@gmail.com")){
            fullName.setText("");
            email.setText("");
            errorMessage.setVisible(true);

        }else{
            errorMessage.setVisible(false);
            fullName.setVisible(false);
            email.setVisible(false);
            confirmBooking.setVisible(false);
            l1.setVisible(false);
            l2.setVisible(false);

            ticketName.setText(userName);
            ticketEvent.setText(currEvent.getTitle());
            ticket.setVisible(true);
            ticketEvent.setVisible(true);
            ticketName.setVisible(true);



            for (int i = 0; i < events.size(); i++){
                if (events.get(i) == currEvent){
                    events.get(i).setCapacity(events.get(i).getCapacity() - 1);
                    ticketsText.setText("Tickets Left: " + events.get(i).getCapacity());
                }
            }
        }

    }

    // switching scenes methods
    public void switchToAdminScene(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Admin.fxml"));
        root = loader.load();
        AdminController adminController = loader.getController();
        adminController.addEvent(events);
        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void switchToUserScene(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("User.fxml"));
        root = loader.load();
        UserController userController = loader.getController();
        userController.displayData(events, currUser);


        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
