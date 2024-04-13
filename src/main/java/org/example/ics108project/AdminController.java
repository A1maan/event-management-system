package org.example.ics108project;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToUserScene(ActionEvent event) throws IOException{
        try {
             root = FXMLLoader.load(getClass().getResource("User.fxml"));
             stage = (Stage)((Node) event.getSource()).getScene().getWindow();
             scene = new Scene(root);
             stage.setScene(scene);
             stage.show();

        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void switchToAddEventScene(ActionEvent event) throws IOException{
        try {
            root = FXMLLoader.load(getClass().getResource("AddEvent.fxml"));
            stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
