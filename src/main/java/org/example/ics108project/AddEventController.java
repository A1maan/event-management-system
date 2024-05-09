package org.example.ics108project;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddEventController implements Initializable {
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

    private ObservableList<Event> currEventList;
    private String[] categoriesArray = {"Entertainment", "Business", "Educational", "Sports", "Food", "Technology", "Fashion", "Festival"};

    public void receiveObsList(ObservableList<Event> eventList){
        currEventList = eventList;
    }
    public Event createEvent(ActionEvent e){
        return new Event(titleField.getText(), categoryField.getValue(), descriptionField.getText(), dateField.getValue(), timeField.getText(), Integer.parseInt(capacityField.getText()), locationField.getText());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        categoryField.getItems().addAll(categoriesArray);
    }

    public void switchToUserScene(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("User.fxml"));

            // the next code block to pass the created event to userController
            root = loader.load();
            UserController userController = loader.getController();
            userController.displayData(currEventList);
            //
            stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void switchToAdminScene(ActionEvent event) throws IOException{
        Event newEvent = createEvent(event);

        currEventList.add(newEvent);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Admin.fxml"));
        root = loader.load();

        AdminController adminController = loader.getController();
        adminController.addEvent(currEventList);

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}
