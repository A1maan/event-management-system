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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.ResourceBundle;

public class AdminController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TableView tableView;
    @FXML
    private TableColumn<Event, Integer> eventIDColumn;
    @FXML
    private TableColumn<Event, String> nameColumn;
    @FXML
    private TableColumn<Event, String> categoryColumn;
    @FXML
    private TableColumn<Event, LocalDate> dateColumn;
    @FXML
    private TableColumn<Event, String> timeColumn;
    @FXML
    private TableColumn<Event, String> locationColumn;
    @FXML
    private TableColumn<Event, Integer> capacityColumn;
    @FXML
    private TableColumn<Event, String> imageColumn;
    ObservableList<Event> events = FXCollections.observableArrayList();
    HashMap<Integer, Event> eventHashMap = new HashMap<>();

    public void addEvent(ObservableList<Event> newEventsList){
        events.addAll(newEventsList);

        for (int i = 0; i < events.size(); i++){
            eventHashMap.put(i + 1, events.get(i));
        }

        tableView.setItems(events);
    }

    public void deleteEvent(ActionEvent event){
        int selectedID = tableView.getSelectionModel().getSelectedIndex();
        tableView.getItems().remove(selectedID);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        eventIDColumn.setCellValueFactory(new PropertyValueFactory<Event, Integer>("eventID"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Event, String>("title"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<Event, String>("category"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<Event, LocalDate>("date"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<Event, String>("time"));
        capacityColumn.setCellValueFactory(new PropertyValueFactory<Event, Integer>("capacity"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<Event, String>("location"));
        imageColumn.setCellValueFactory(new PropertyValueFactory<Event, String>("imageNameText"));
    }

    public void switchToUserScene(ActionEvent event) throws IOException{
        try {
            events = tableView.getItems();

            if (events != null){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("User.fxml"));
                // the next code block to pass the created event to userController
                root = loader.load();
                UserController userController = loader.getController();
                userController.displayData(events);
                //
                stage = (Stage)((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
            else{
                root = FXMLLoader.load(getClass().getResource("User.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void switchToAddEventScene(ActionEvent event) throws IOException{
        try {
            events = tableView.getItems();

            if (events != null) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(("AddEvent1.fxml")));
                root = loader.load();

                AddEventController addEventController = loader.getController();
                addEventController.receiveObsList(events);

                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }

            else{
                root = FXMLLoader.load(getClass().getResource("AddEvent1.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }

        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void switchToEditEventScene(ActionEvent event) throws IOException {
        int selectedID = tableView.getSelectionModel().getSelectedIndex(); // Returned index from [0, numOfRows - 1]
        Event selectedEvent = eventHashMap.get(selectedID + 1);

        FXMLLoader loader = new FXMLLoader(getClass().getResource(("EditEvent.fxml")));
        root = loader.load();

        EditEventController editEventController = loader.getController();
        editEventController.setupEditScene(selectedID, selectedEvent, eventHashMap, events);

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}
