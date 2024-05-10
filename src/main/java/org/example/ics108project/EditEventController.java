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
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;


public class EditEventController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TextField titleField;
    @FXML
    private TextArea descriptionField;
    @FXML
    private ChoiceBox<String> categoryField;
    @FXML
    private DatePicker dateField;
    @FXML
    private TextField timeField;
    @FXML
    private TextField capacityField;
    @FXML
    private TextArea locationField;

    @FXML
    private Text invalidText;

    ObservableList<Event> oldEvents = FXCollections.observableArrayList();
    Event oldEvent;
    Integer selectedEventIndex;
    HashMap<Integer, Event> eventHashMap = new HashMap<>();

    private String[] categoriesArray = {"Entertainment", "Business", "Educational", "Sports", "Food", "Technology", "Fashion", "Festival"};

    public void setupEditScene(Integer eventIndex, Event event, HashMap<Integer, Event> eventHashMap, ObservableList<Event> events){
        oldEvent = event;
        oldEvents = events;

        titleField.setText(event.getTitle());
        titleField.setEditable(true);

        descriptionField.setText(event.getDescription());
        descriptionField.setEditable(true);

        categoryField.setValue(event.getCategory());

        dateField.setValue(event.getDate());
        dateField.setEditable(true);

        timeField.setText(event.getTime());
        timeField.setEditable(true);

        capacityField.setText(String.valueOf(event.getCapacity()));
        capacityField.setEditable(true);

        locationField.setText(event.getLocation());
        locationField.setEditable(true);

        selectedEventIndex = eventIndex;
        eventHashMap = eventHashMap;

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        categoryField.getItems().addAll(categoriesArray);
    }

    public Event editEvent(Event event){
        event.setTitle(titleField.getText());
        event.setDescription(descriptionField.getText());
        event.setCategory(categoryField.getValue());
        event.setDate(dateField.getValue());
        event.setTime(timeField.getText());
        event.setCapacity(Integer.parseInt(capacityField.getText()));
        event.setLocation(locationField.getText());

        return event;
    }
    public void switchToAdminScene(ActionEvent e) throws IOException {
        LocalDate currDate = LocalDate.now();

        if (dateField.getValue().isBefore(currDate)){
            invalidText.setVisible(true);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Date");
            alert.setHeaderText("Invalid Date");
            alert.setContentText("Cannot choose a date before the current/present date!\nPlease choose either today's date or a future date.");
            alert.showAndWait();
        }

        else {
            Event edittedEvent = editEvent(oldEvent);

            eventHashMap.replace(selectedEventIndex, edittedEvent);

            oldEvents.addAll(eventHashMap.values());

            FXMLLoader loader = new FXMLLoader(getClass().getResource("Admin.fxml"));
            root = loader.load();

            AdminController adminController = loader.getController();
            adminController.addEvent(oldEvents);

            stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }
}
