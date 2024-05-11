# Event Management System
## Table of Contents
- [Description](#description)
- [Technologies Used](#technologies-used)
- [Design Overview](#design-overview)
- [Running the Application](#running-the-application)
- [Usage](#usage)

## Description
The Event Management System is a simple JavaFX application designed to create and manage events. The application allows the admin to create, edit and delete events. The system also allows users to register/book tickets for upcoming events and view the events they have already registered for.

## Technologies Used
- **Java**: Core programming language used for building the application logic.
- **JavaFX**: Graphical User Interface (GUI) toolkit for Java used to create the desktop application interface.
- **SceneBuilder**: Graphical User Interface (GUI) design tool as a complementary aid tool to JavaFX.

## Design Overview
The application is structured into several classes, each responsible for specific aspects of the
application:


- **Main**: The entry point of the application which launches the JavaFX application.
    - **Variables**: `Stage primaryStage`, `Parent root`, `Scene scene`
    
    - **Methods**: `start(Stage primaryStage)`


- **Event**: A java class representing a single event.
    - **Variables**: `static Integer id `, `private Integer eventId`, `private String title`, `private String description`,`private String category`, `private LocalDate date`, `private String time`, `private Integer capacity`, `private String location`

    - **Methods**: `public Event(String title, String category, String description, LocalDate date, String time, Integer capacity, String location )`, `public static Integer getcurrId()`, `public Integer getEventId()`, `public void setEventId(Integer eventId)`, `public void setTitle(String title)`, `public void setDescription(String description)`, `public void setCategory(String category)`, `public void setDate(LocalDate date)`, `public void setTime(String time)`, `public void setCapacity(Integer capacity)`, `public void setLocation(String location)`, `public String getTitle()`, `public String getDescription()`, `public String getCategory()`, `public LocalDate getDate()`, `public Integer getCapacity()`, `public String getLocation()`, `public String getTime()`, `public Integer getEventID()`


- **AdminController**: The controller class for the Admin scene/page.
    - **Variables**: `Stage primaryStage`, `Parent root`, `Scene scene`, `TableView tableView`,`TableColumn<Event, Integer> eventIDColumn`, `TableColumn<Event, String> nameColumn`, `TableColumn<Event, String> categoryColumn`, `TableColumn<Event, LocalDate> dateColumn`, `TableColumn<Event, String> timeColumn`, `TableColumn<Event, String> locationColumn`, `TableColumn<Event, Integer> capacityColumn`, `ObservableList<Event> events`, ` HashMap<Integer, Event> eventHashMap`

    - **Methods**: `addEvent(ObservableList<Event> newEventsList)`, `deleteEvent(ActionEvent event)`, `initialize(URL url, ResourceBundle resourceBundle)`, `switchToUserScene(ActionEvent event)`, `switchToAddEventScene(ActionEvent event)`, `switchToEditEventScene(ActionEvent event)`


- **AddEventController**: The controller class for the Add Event scene/page.

    - **Variables**: `Stage stage`, `Scene scene`, `Parent root`, `TextField titleField`, `TextArea descriptionField`, `ChoiceBox<String> categoryField`, `DatePicker dateField`, `TextField timeField`, `TextField capacityField`, `TextArea locationField`, `Text invalidText`, `ObservableList<Event> currEventList`, `String[] categoriesArray`

    - **Methods**: `receiveObsList(ObservableList<Event> eventList)`, `createEvent(ActionEvent e)`, `initialize(URL url, ResourceBundle resourceBundle)`, `switchToAdminScene(ActionEvent event)`


- **EditEventController**: The controller class for the Edit Event scene/page.

    - **Variables**: `Stage stage`, `Scene scene`, `Parent root`, `TextField titleField`, `TextArea descriptionField`, `ChoiceBox<String> categoryField`, `DatePicker dateField`, `TextField timeField`, `TextField capacityField`, `TextArea locationField`, `Text invalidText`, `ObservableList<Event> oldEvents`, `Event oldEvent`, `Integer selectedEventIndex`, `HashMap<Integer, Event> eventHashMap`, `private String[] categoriesArray`

    - **Methods**: `setupEditScene(Integer eventIndex, Event event, HashMap<Integer, Event> eventHashMap, ObservableList<Event> events)`, `initialize(URL url, ResourceBundle resourceBundle)`, `editEvent(Event event)`, `switchToAdminScene(ActionEvent e)`

- **UserController**: The controller class for the User scene/page.
    - **Variables**: 
    - **Methods**: 

- **EventCardController**: The controller class for the Event Card.
    - **Variables**: 
    - **Methods**: 

- **EventDetailsCont**: The controller class for the Event Details scene/page.
    - **Variables**: 
    - **Methods**: 

### Running the Application
To run the application, you will need to have Java and JavaFX set up on your machine. Once
installed, you can compile and run the application from your IDE.

### Usage
1. **Enter Admin Scene**: Start by entering the Admin page.
2. **Create Event**: Start by creating events in the admin page.
3. **Edit Event (Optional)**: Edit the contents of events (if needed).
4. **Delete Event (Optional)**: Delete events (if needed).
5. **Switch to User Scene**: After creating and managing the events, switch to the Home (User) page.
6. **Apply Category Filters (Optional)**: Apply filters for the type of events to register/book.
7. **Apply Date Filters (Optional)**: Apply filters for the date of events to register/book.
8. **View Event Details**: Click on "More" to view more event details and to register/book the event tickets.
9. **Book Tickets**: Click on "Book Your Ticket" and enter User name and User email ID (Gmail) to book tickets.
10. **Confirm Booking**: Click "Confirm" to confirm booking of the ticket.
