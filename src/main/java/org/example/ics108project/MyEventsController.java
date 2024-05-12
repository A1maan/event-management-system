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
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MyEventsController implements Initializable { // this class controlls the users booked events

    private ObservableList<Event> bookedEvents = FXCollections.observableArrayList();
    private ObservableList<Event> events = FXCollections.observableArrayList();
    private Users currUser;
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    Label eMessage;
    @FXML
    ImageView ePhoto;
    @FXML
    VBox eventLayout;
    @FXML
    Button logginButton;
    @FXML
    ImageView userImage;
    @FXML
    AnchorPane popup;
    @FXML
    Label ticketEvent;
    @FXML
    Label ticketName;
    @FXML
    Button quitButton;
    @FXML
    Rectangle hideBox;
    @FXML
    Button confirmCancel;
    @FXML
    Label ticketLabel;
    @FXML
    ImageView noBookingsImage;
    @FXML
    VBox containerBox;


    public void setData(Users loggedUser, ObservableList<Event> event){
        events.addAll(event);
        System.out.println(events);
        currUser = loggedUser;
        if (currUser == null){
            eMessage.setVisible(true);
            ePhoto.setVisible(true);
        }else{
            bookedEvents = currUser.getUserEvents();

            try {
                userImage.setImage(currUser.getUserImage());
                logginButton.setText(currUser.getUserName());

                if (bookedEvents.isEmpty()){
                    noBookingsImage.setVisible(true);
                    containerBox.setVisible(false);
                } else{
                    for (Event ev : bookedEvents) {
                        FXMLLoader fxmLoader = new FXMLLoader();
                        fxmLoader.setLocation(getClass().getResource("BookedEventsCard.fxml"));
                        VBox eventBox = fxmLoader.load();
                        BookingCardController bookingCardController = fxmLoader.getController();
                        bookingCardController.displayEvents(ev);
                        bookingCardController.setCurrUser(currUser);
                        bookingCardController.saveData(events);
                        eventLayout.getChildren().add(eventBox);
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

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
    //switching methods
    public void switchToLoginScene(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
        root = loader.load();

        LoginController loginController = loader.getController();
        loginController.checker(logginButton.getText(), events);

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

    public void cancelConfirmation(ActionEvent e) throws IOException { // this method not working properly yet
        String eventTitle = ticketEvent.getText();
        for(Event ev: currUser.getUserEvents()){
            if (ev.getTitle().equals(eventTitle)){
                currUser.removeUserEvent(ev);
                ev.setCapacity(ev.getCapacity() + 1);
                break;
            }
        }

        popup.setVisible(false);
        hideBox.setVisible(false);

        bookedEvents = currUser.getUserEvents();
        System.out.println(bookedEvents);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("MyEvents.fxml"));
        root = loader.load();

        MyEventsController myEventsController = loader.getController();
        myEventsController.setData(currUser, events);

        stage = (Stage)((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    //ticket view and cancel booking methods
    public void ticketView(Event event, Users loggedUser) {
        currUser = loggedUser;
        popup.setVisible(true);
        ticketEvent.setText(event.getTitle());
        ticketName.setText(currUser.getUserName());
        hideBox.setVisible(true);
        ticketLabel.setText("Ticket View");
        confirmCancel.setVisible(false);
    }

    public void closePopup(MouseEvent mouseEvent){
        popup.setVisible(false);
        hideBox.setVisible(false);
    }

    public void cancelView(Event event, Users loggedUser) {
        currUser = loggedUser;
        popup.setVisible(true);
        ticketEvent.setText(event.getTitle());
        ticketName.setText(currUser.getUserName());
        hideBox.setVisible(true);
        ticketLabel.setText("Cancel Your Booking?");

        confirmCancel.setVisible(true);
    }
}
