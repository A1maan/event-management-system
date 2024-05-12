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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    static ObservableList<Users> users = FXCollections.observableArrayList();
    static ObservableList<Event> events = FXCollections.observableArrayList();

    private Stage stage;
    private Scene scene;
    private Parent root;

    private boolean flag = false;

    private Users currUser;

    private Image image;

    @FXML
    TextField userName;
    @FXML
    TextField userEmail;
    @FXML
    PasswordField userPassword;
    @FXML
    ImageView userImage;
    @FXML
    ImageView userImage2;
    @FXML
    Button saveUser;
    @FXML
    Button uploadImage;
    @FXML
    Button loginButton;
    @FXML
    Button updateUser;
    @FXML
    Button logOut;
    @FXML
    Label sMessage;

    public void createUser(ActionEvent event){

        try{
            Users newUser = new Users(userName.getText(), userEmail.getText(), userPassword.getText(), userImage.getImage());
            users.add(newUser);
            currUser = newUser;
            userImage2.setImage(newUser.getUserImage());
            loginButton.setText(newUser.getUserName());

            sMessage.setVisible(true);
            sMessage.setText("Welcome to the app " + userName.getText());

        } catch (Exception exception){
            System.out.println(exception);
        }


    }

    public void updateUser(ActionEvent event){
        currUser.setUserImage(userImage.getImage());
        userImage2.setImage(userImage.getImage());
        loginButton.setText(userName.getText());
        currUser.setUserName(userName.getText());
        currUser.setUserEmail(userEmail.getText());
        currUser.setUserPassword(userPassword.getText());

        sMessage.setVisible(true);
        sMessage.setText("Your info is updated");
    }

    public void logout(ActionEvent event){
        users.remove(currUser);
        Image im = new Image("C:\\Users\\Dr.Rehab Gwada\\OneDrive\\Desktop\\java project\\EventManagementSystem\\src\\main\\resources\\org\\example\\ics108project\\project-images\\profilePic.png");
        userImage.setImage(im);
        userImage2.setImage(im);

        loginButton.setText("Log In");
        userName.setText("");
        userEmail.setText("");
        userPassword.setText("");
        logOut.setVisible(false);
        saveUser.setVisible(true);
        updateUser.setVisible(false);
        sMessage.setVisible(false);
    }

    public void setImage(ActionEvent event){

        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter imageFilter =
                new FileChooser.ExtensionFilter("Image Files (*.jpg, *.png, *.gif)", "*.jpg", "*.png", "*.gif");
        fileChooser.getExtensionFilters().add(imageFilter);

        File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null) {
            try {
                String fileName = selectedFile.getName();
                image = new Image(selectedFile.toURI().toString());
                userImage.setImage(image);

            } catch (Exception exception) {
                image = new Image("C:\\Users\\Dr.Rehab Gwada\\OneDrive\\Desktop\\java project\\EventManagementSystem\\src\\main\\resources\\org\\example\\ics108project\\project-images\\profilePic.png");
                userImage.setImage(image);
            }
        }

    }

    public void checker(String currUserName,ObservableList<Event> currEventsList ){
        events.addAll(currEventsList);
        loginButton.setText(currUserName);
        for(Users eachUser: users){
            if(eachUser.getUserName().equals(currUserName)){
                flag = true;
                currUser = eachUser;
                break;
            }
        }

        if (flag){
            userImage.setImage(currUser.getUserImage());
            userName.setText(currUser.getUserName());
            userEmail.setText(currUser.getUserEmail());
            logOut.setVisible(true);
            updateUser.setVisible(true);
            saveUser.setVisible(false);
        }
    }

    public void switchToUserScene(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("User.fxml"));
        // the next code block to pass the created event to userController
        root = loader.load();
        UserController userController = loader.getController();
        userController.displayData(events, currUser);
        //
        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
