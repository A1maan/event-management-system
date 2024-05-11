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
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class UserController implements Initializable  {

    ObservableList<Event> events = FXCollections.observableArrayList();
    ObservableList<Event> filterEvents = FXCollections.observableArrayList();

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    VBox eventLayout;
    @FXML
    TextField searchInput;
    @FXML
    ImageView errorFilter;
    @FXML
    DatePicker datePicker;
    @FXML
    Label dateLabel;
    @FXML
    CheckBox busTag;
    @FXML
    CheckBox eduTag;
    @FXML
    CheckBox entTag;
    @FXML
    CheckBox fashTag;
    @FXML
    CheckBox fesTag;
    @FXML
    CheckBox sprTag;
    @FXML
    CheckBox techTag;
    @FXML
    CheckBox foodTag;
    public void displayData(ObservableList<Event> event){
        events.addAll(event);
        try {
            for (Event value : events) {
                FXMLLoader fxmLoader = new FXMLLoader();
                fxmLoader.setLocation(getClass().getResource("EventCard.fxml"));
                VBox eventBox = fxmLoader.load();
                EventCardController eventCardController = fxmLoader.getController();
                eventCardController.setData(value);
                eventLayout.getChildren().add(eventBox);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void applyFilter(){
        while(!eventLayout.getChildren().isEmpty()){
            eventLayout.getChildren().removeFirst();
        }

        try {
            for (Event value : filterEvents) {
                FXMLLoader fxmLoader = new FXMLLoader();
                fxmLoader.setLocation(getClass().getResource("EventCard.fxml"));
                VBox eventBox = fxmLoader.load();
                EventCardController eventCardController = fxmLoader.getController();
                eventCardController.setData(value);
                eventLayout.getChildren().add(eventBox);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        errorFilter.setVisible(eventLayout.getChildren().isEmpty());
        while(!filterEvents.isEmpty()){
            filterEvents.removeFirst();
        }
        System.out.println(filterEvents);
    }

    public void searchFilter() throws IOException {

        String sInput = searchInput.getText();
        sInput = sInput.toLowerCase();
        if (!sInput.isEmpty()){
            for (Event event : events) {
                if (event.getTitle().toLowerCase().equals(sInput) && !filterEvents.contains(event)) {
                    filterEvents.add(event);
                }
            }
        }
        applyFilter();
    }

    public void dateFilter(){

        LocalDate chosenDate = datePicker.getValue();
        String formattedDate = chosenDate.format(DateTimeFormatter.ofPattern("MMM-dd-yyyy"));
        dateLabel.setText(formattedDate);

        for (Event event : events) {
            if (!event.getDate().toString().equals(chosenDate.toString()) && !filterEvents.contains(event)) {
                filterEvents.add(event);
            }
        }
        applyFilter();
    }

    public void tagFilter(){
        HashMap<String, Boolean> tags = new HashMap<String, Boolean>();
        tags.put(busTag.getText(), busTag.isSelected());
        tags.put(eduTag.getText(), eduTag.isSelected());
        tags.put(entTag.getText(), entTag.isSelected());
        tags.put(fashTag.getText(), fashTag.isSelected());
        tags.put(fesTag.getText(), fesTag.isSelected());
        tags.put(foodTag.getText(), foodTag.isSelected());
        tags.put(sprTag.getText(), sprTag.isSelected());
        tags.put(techTag.getText(), techTag.isSelected());

        for(String key: tags.keySet()){
            if (tags.get(key)){
                for (Event event: events){
                    if (event.getCategory().equals(key)){
                        filterEvents.add(event);
                    }
                }
            } else{
                for (Event event: filterEvents){
                    if (event.getCategory().equals(key)){
                        filterEvents.remove(event);
                    }
                }
            }
        }
        applyFilter();

    }

    public void resetFilters(){
        try {
            while(!eventLayout.getChildren().isEmpty()){
                eventLayout.getChildren().removeFirst();
            }

            errorFilter.setVisible(false);
            searchInput.setText("");
            dateLabel.setText("Date Filter");
            busTag.setSelected(true);
            entTag.setSelected(true);
            eduTag.setSelected(true);
            fashTag.setSelected(true);
            fesTag.setSelected(true);
            foodTag.setSelected(true);
            sprTag.setSelected(true);
            techTag.setSelected(true);

            for (Event value: events) {
                FXMLLoader fxmLoader = new FXMLLoader();
                fxmLoader.setLocation(getClass().getResource("EventCard.fxml"));
                VBox eventBox = fxmLoader.load();
                EventCardController eventCardController = fxmLoader.getController();
                eventCardController.setData(value);
                eventLayout.getChildren().add(eventBox);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
