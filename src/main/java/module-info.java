module org.example.ics108project {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.ics108project to javafx.fxml;
    exports org.example.ics108project;
}