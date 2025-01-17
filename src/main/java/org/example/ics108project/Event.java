package org.example.ics108project;

import javafx.scene.image.Image;

import java.time.LocalDate;
import java.util.Date;

public class Event {
    static Integer id = 0;
    private Integer eventId;
    private String title;
    private String description;
    private String category;
    private LocalDate date;
    private String time;
    private Integer capacity;
    private String location;
    private Image image;
    private String imageNameText;

    public Event(String title, String category, String description, LocalDate date, String time, Integer capacity, String location, Image image, String imageNameText){
        id += 1;

        this.title = title;
        this.category = category;
        this.description = description;
        this.date = date;
        this.time = time;
        this.capacity = capacity;
        this.location = location;
        this.image = image;
        this.imageNameText = imageNameText;
        this.eventId = id;
    }

    public static Integer getcurrId(){
        return id;
    }

    public Integer getEventId(){
        return this.eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public LocalDate getDate() {
        return date;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public String getLocation() {
        return location;
    }
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
    public Image getImage() {
        return image;
    }
    public void setImage(Image image) {
        this.image = image;
    }
    public String getImageNameText() {
        return imageNameText;
    }
    public void setImageNameText(String imageNameText) {
        this.imageNameText = imageNameText;
    }


    public Integer getEventID(){
        return this.eventId;
    }

}
