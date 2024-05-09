package org.example.ics108project;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class eventCardController implements Initializable {
    @FXML
    ImageView eventImage;
    @FXML
    Label eventDate;
    @FXML
    Label eventTickets;
    @FXML
    Label eventTitle;

    @Override
    public void initialize(URL url, ResourceBundle rb){
        InputStream stream = null;
        try {
            stream = new FileInputStream("C:\\Users\\Dr.Rehab Gwada\\OneDrive\\Desktop\\java project\\EventManagementSystem\\src\\main\\resources\\org\\example\\ics108project\\project-images\\defualtImage.jpg");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        Image image = new Image(stream);
        eventImage.setImage(image);

        Rectangle clip = new Rectangle();
        clip.setWidth(125.0f);
        clip.setHeight(110.0f);

        clip.setArcHeight(20);
        clip.setArcHeight(20);

        clip.setStroke((Color.BLACK));
        eventImage.setClip(clip);

        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);
        WritableImage photo = eventImage.snapshot(parameters, null);

        eventImage.setClip(null);
        eventImage.setEffect(new DropShadow(20, Color.BLACK));
        eventImage.setImage(photo);

    }

    public void setData(Event event){
        InputStream stream = null;
        try {
            stream = new FileInputStream("C:\\Users\\Dr.Rehab Gwada\\OneDrive\\Desktop\\java project\\EventManagementSystem\\src\\main\\resources\\org\\example\\ics108project\\project-images\\defualtImage.jpg");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        Image image = new Image(stream);


    }
}
