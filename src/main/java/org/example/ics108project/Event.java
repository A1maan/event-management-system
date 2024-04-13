package org.example.ics108project;

public class Event {
    static Integer id;
    private Integer eventId;
    private String title;
    private String description;
    private String category;
    private String date;
    private Integer capacity;
    private String location;

    public Event(String title, String category, String description, String date, Integer capacity, String location ){
        this.title = title;
        this.category = category;
        this.description = description;
        this.date = date;
        this.capacity = capacity;
        this.location = location;
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

    public void setDate(String date) {
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

    public String getDate() {
        return date;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public String getLocation() {
        return location;
    }

}
