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
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

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
    private Image image;
    @FXML
    private Label imageNameLabel;

    @FXML
    private Text invalidText;

    private ObservableList<Event> currEventList;
    private String[] categoriesArray = {"Entertainment", "Business", "Educational", "Sports", "Food", "Technology", "Fashion", "Festival"};

    public void receiveObsList(ObservableList<Event> eventList){
        currEventList = eventList;
    }

    public void receiveImagePath(ActionEvent e){
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter imageFilter =
                new FileChooser.ExtensionFilter("Image Files (*.jpg, *.png, *.gif)", "*.jpg", "*.png", "*.gif");
        fileChooser.getExtensionFilters().add(imageFilter);

        File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null) {
            try {
                String fileName = selectedFile.getName();
                imageNameLabel.setText(fileName);
                image = new Image(selectedFile.toURI().toString());
            } catch (Exception exception) {
                image = new Image("/Users/almaan/Library/CloudStorage/OneDrive-KFUPM/Class Notes/Term-232/ICS108/ICS108-Project/src/main/resources/org/example/ics108project/project-images/defualtImage.jpg");
            }
        }
    }
    public Event createEvent(ActionEvent e){
        return new Event(titleField.getText(), categoryField.getValue(), descriptionField.getText(), dateField.getValue(), timeField.getText(), Integer.parseInt(capacityField.getText()), locationField.getText(), image, imageNameLabel.getText());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        categoryField.getItems().addAll(categoriesArray);
    }

    public void switchToAdminScene(ActionEvent event) throws IOException{
        LocalDate currDate = LocalDate.now();

        if (dateField.getValue().isBefore(currDate)){
            invalidText.setVisible(true);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Date");
            alert.setHeaderText("Invalid Date");
            alert.setContentText("Cannot choose a date before the current/present date!\nPlease choose either today's date or a future date.");
            alert.showAndWait();
        }
        else{
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
}
