
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
    
    - **Methods**: `start(Stage primaryStage)` , `main(String[] args)`


- **Event**: A java class representing a single event.
    - **Variables**:  `static Integer id`, `private Integer eventId`, `private String title`, `private String description`,`private String category`, `private LocalDate date`, `private String time`, `private Integer capacity`, `private String location`, `private Image image`, `private String imageNameText`

    - **Methods**: `public Event(String title, String category, String description, LocalDate date, String time, Integer capacity, String location )`, `setEventId(Integer eventId)`, `setTitle(String title)`, `setDescription(String description)`, `setCategory(String category)`, `setDate(LocalDate date)`, `setTime(String time)`, `setCapacity(Integer capacity)`, `setLocation(String location)`, `getcurrId()`, `getEventId()`, `getTitle()`, `getDescription()`, `getCategory()`, `getDate()`, `getCapacity()`, `getLocation()`, `getTime()`, `getEventID()`


- **Users**: A java class representing the user.
    - **Variables**: `static String userName`, `static String userEmail`, `static private String userPassword`, `static Image userImage`, `ObservableList<Event> userEvents`

    - **Methods**: `Users(String name, String email, String password, Image image)`, `setUserEvents(Event events)`, `removeUserEvent(Event event)`, `getUserEvents()`, `setUserName(String name)`, `setUserEmail(String email)`, `setUserPassword(String password)`, `setUserImage(Image image)`, `getUserName()`, `getUserEmail()`, `getUserImage()`


- **AdminController**: The controller class for the Admin scene/page.
    - **Variables**: `private Stage primaryStage`, `private Parent root`, `private Scene scene`, `private TableView tableView`,`private TableColumn<Event, Integer> eventIDColumn`, `private TableColumn<Event, String> nameColumn`, `private TableColumn<Event, String> categoryColumn`, `private TableColumn<Event, LocalDate> dateColumn`, `private TableColumn<Event, String> timeColumn`, `private TableColumn<Event, String> locationColumn`, `private TableColumn<Event, Integer> capacityColumn`, `private TableColumn<Event, String> imageColumn`, `private ObservableList<Event> events`, `private HashMap<Integer, Event> eventHashMap`

    - **Methods**: `addEvent(ObservableList<Event> newEventsList)`, `deleteEvent(ActionEvent event)`, `initialize(URL url, ResourceBundle resourceBundle)`, `switchToUserScene(ActionEvent event)`, `switchToAddEventScene(ActionEvent event)`, `switchToEditEventScene(ActionEvent event)`


- **AddEventController**: The controller class for the Add Event scene/page.

    - **Variables**: `private Stage stage`, `private Scene scene`, `private Parent root`, `private TextField titleField`, `private TextArea descriptionField`, `private ChoiceBox<String> categoryField`, `private DatePicker dateField`, `private TextField timeField`, `private TextField capacityField`, `private TextArea locationField`, `private Image image`, `private Label imageNameLabel`,  `private Text invalidText`, `private ObservableList<Event> currEventList`, `private String[] categoriesArray`

    - **Methods**: `receiveObsList(ObservableList<Event> eventList)`, `createEvent(ActionEvent e)`, `receiveImagePath(ActionEvent e)`,  `initialize(URL url, ResourceBundle resourceBundle)`, `switchToAdminScene(ActionEvent event)`


- **EditEventController**: The controller class for the Edit Event scene/page.

    - **Variables**: `Stage stage`, `Scene scene`, `Parent root`, `TextField titleField`, `TextArea descriptionField`, `ChoiceBox<String> categoryField`, `DatePicker dateField`, `TextField timeField`, `TextField capacityField`, `TextArea locationField`, `Text invalidText`, `ObservableList<Event> oldEvents`, `Event oldEvent`, `Integer selectedEventIndex`, `HashMap<Integer, Event> eventHashMap`, `private String[] categoriesArray`

    - **Methods**: `setupEditScene(Integer eventIndex, Event event, HashMap<Integer, Event> eventHashMap, ObservableList<Event> events)`, `initialize(URL url, ResourceBundle resourceBundle)`, `editEvent(Event event)`, `switchToAdminScene(ActionEvent e)`


- **UserController**: The controller class for the User scene/page.
    - **Variables**: `ObservableList<Event> events`, `ObservableList<Event> filterEvents`, `static ObservableList<Users> users`, `private Stage stage`, `private Scene scene`, `private Parent root`, `private Users currUser`, `VBox eventLayout`, `TextField searchInput`, `ImageView errorFilter`, `DatePicker datePicker`, `Label dateLabel`, `CheckBox busTag`, `CheckBox eduTag`, `CheckBox entTag`, `CheckBox fashTag`, `CheckBox fesTag`, `CheckBox sprTag`, `CheckBox techTag`, `CheckBox foodTag`, `Button loginButton`, `ImageView userImage`

    - **Methods**: `initialize(URL url, ResourceBundle resourceBundle)`, `displayData(ObservableList<Event> event)`, `applyFilter()`, `searchFilter()`, `dateFilter()`, `tagFilter()`, `resetFilters()`, `switchToAdminScene(ActionEvent event)`


- **EventCardController**: The controller class for the Event Card.
    - **Variables**: `private static ObservableList<Event> events`, `private ObservableList<Event> users`, `private Users currUser`, `private Stage stage`, `private Scene scene`, `private Parent root`, `ImageView eventImage`, `Label eventDate`, `Label eventTickets`, `Label eventTitle`

    - **Methods**: `initialize(URL url, ResourceBundle rb)`, `setCurrUser(Users loggedUser)`, `setData(Event event)`, `switchToEventDetailsScene(ActionEvent event)`


- **EventDetailsCont**: The controller class for the Event Details scene/page.
    - **Variables**: `ObservableList<Event> events`, `Event currEvent`, `static ObservableList<Users> users`, `private Users currUser`, `boolean alrdyConfirmed`, `private Stage stage`, `private Scene scene`, `private Parent root`, `Label titleText`, `Label descriptionText`, `Label dateText`, `Label timeText`, `Label locationText`, `Label categoryText`, `Label ticketsText`, `Button bookingButton`, `AnchorPane confirmAnchor`, `Rectangle coverScreen`, `ImageView cancelBooking`, `Button confirmBooking`, `TextField fullName`, `TextField email`, `Label errorMessage`, `ImageView ticket`, `Label ticketName`, `Label ticketEvent`, `Label l1`, `Label l2`, `ImageView eventDetailsImage`, `ImageView userImage`, `Button loginButton`, `ImageView confirmPhoto`, `ImageView ePhoto`

    - **Methods**: `initialize(URL url, ResourceBundle resourceBundle)`, `saveData(ObservableList<Event> passedEvents)`, `displayData(Event event, ObservableList<Event> events)`, `openConfirmation(ActionEvent event)`, `cancelConfirmation()`, `sendTicket()`, `switchToAdminScene(ActionEvent event)`, `switchToUserScene(ActionEvent event)`, `switchToMyEventsScene(ActionEvent event)`, `switchToLoginScene(ActionEvent event)`


- **BookingCardController**: The controller class for the Booking Card.

    - **Variables**: `private Stage stage`, `private Scene scene`, `private Parent root`, `ImageView eventImage`, `ImageView cancelButton`, ` Button ticketButton`, `Label eventTitle`, `Label eventDate`, `Label eventTickets`, `private ObservableList<Event> bookedEvents`, `private Users currUser`

    - **Methods**: `displayEvents(Event event)`, `setCurrUser(Users loggedUser)`, `initialize(URL url, ResourceBundle resourceBundle)`, `ticketView(ActionEvent e)`, `cancelBooking(MouseEvent e)`, `switchToEventDetailsScene(ActionEvent event)`


- **LoginController**: The controller class for Login scene/page.

    - **Variables**: `static ObservableList<Users> users`, `ObservableList<Event> events`, `private Stage stage`, `private Scene scene`, `private Parent root`, `private boolean flag`, `private Users currUser`, `private Image image`, `TextField userName`, `TextField userEmail`, `PasswordField userPassword`, `ImageView userImage`, `ImageView userImage2`, `Button saveUser`, `Button uploadImage`, `Button loginButton`, `Button updateUser`,  `Button logOut`, `Label sMessage`, `CheckBox changePass`, `Label eMessageP`, `Label eMessageE`, `Label eMessageN`

    - **Methods**: `initialize(URL url, ResourceBundle resourceBundle)`, `createUser(ActionEvent event)`, `updateUser(ActionEvent event)`, `logout(ActionEvent event)`, `checker(String currUserName,ObservableList<Event> currEventsList )`, `changePassword(ActionEvent event)`, `switchToUserScene(ActionEvent event)`, `switchToMyEventsScene(ActionEvent event)`, `switchToAdminScene(ActionEvent event)`

- **MyEventsController**: The controller class for the My Events scene/page.

    - **Variables**: `private ObservableList<Event> bookedEvents`, `private ObservableList<Event> events`, `private Users currUser`,  `private Stage stage`, `private Scene scene`, `private Parent root`, `Label eMessage`, `ImageView ePhoto`, `VBox eventLayout`, `Button logginButton`, `ImageView userImage`, `AnchorPane popup`, `Label ticketEvent`, `Label ticketName`, `Button quitButton`, `Rectangle hideBox`, `Button confirmCancel`, `Label ticketLabel`

    - **Methods**: `initialize(URL url, ResourceBundle resourceBundle)`, `setData(Users loggedUser, ObservableList<Event> event)`, `switchToAdminScene(ActionEvent event)`, `switchToLoginScene(ActionEvent event)`, `switchToUserScene(ActionEvent event)`, `cancelConfirmation(ActionEvent e)`, `sticketView(Event event)`, `closePopup(MouseEvent mouseEvent)`, `cancelView(Event event)`

### Running the Application
To run the application, you will need to have Java and JavaFX set up on your machine. Once
installed, you can compile and run the application from your IDE.

### Usage
1. **Enter Admin Scene**: Start by entering the Admin page.
![Admin Page](src/main/resources/org/example/ics108project/project-images/readme-images/Screenshot 2024-05-12 at 23.04.36.png)
2. **Create Event**: Start by creating events and receiving confirmation alerts in the admin page.
![Add Event](src/main/resources/org/example/ics108project/project-images/readme-images/Screenshot 2024-05-12 at 23.12.58.png)
![Add Event Confirmation Alert](src/main/resources/org/example/ics108project/project-images/readme-images/Screenshot 2024-05-12 at 23.14.50.png)
3. **Edit Event (Optional)**: Edit the contents of events (if needed).
![Edit Event](src/main/resources/org/example/ics108project/project-images/readme-images/Screenshot 2024-05-12 at 23.18.46.png)
![Edit Event Confirmation Alert](src/main/resources/org/example/ics108project/project-images/readme-images/Screenshot 2024-05-12 at 23.19.44.png)
4. **Delete Event (Optional)**: Delete events (if needed).
![Delete Event](src/main/resources/org/example/ics108project/project-images/readme-images/Screenshot 2024-05-12 at 23.22.08.png)
![Delete Event](src/main/resources/org/example/ics108project/project-images/readme-images/Screenshot 2024-05-12 at 23.23.07.png)
![Delete Event Confirmation](src/main/resources/org/example/ics108project/project-images/readme-images/Screenshot 2024-05-12 at 23.24.40.png)
5. **Switch to User Scene**: After creating and managing the events, switch to the Home (User) page.
![Home Page](src/main/resources/org/example/ics108project/project-images/readme-images/Screenshot 2024-05-12 at 23.26.10.png)
![Home Page](src/main/resources/org/example/ics108project/project-images/readme-images/Screenshot 2024-05-12 at 23.28.18.png)
6. **Login**: Login to your user account
![Login](src/main/resources/org/example/ics108project/project-images/readme-images/Screenshot 2024-05-12 at 23.41.39.png)
![Login](src/main/resources/org/example/ics108project/project-images/readme-images/Screenshot 2024-05-12 at 23.43.05.png)
![Login](src/main/resources/org/example/ics108project/project-images/readme-images/Screenshot 2024-05-12 at 23.44.02.png)
7. **Apply Category Filters (Optional)**: Apply filters for the type of events to register/book.
![Category Filters](src/main/resources/org/example/ics108project/project-images/readme-images/Screenshot 2024-05-12 at 23.45.03.png)
8. **Apply Date Filters (Optional)**: Apply filters for the date of events to register/book.
![Date Filters](src/main/resources/org/example/ics108project/project-images/readme-images/Screenshot 2024-05-12 at 23.46.31.png)
9. **View Event Details**: Click on "More" to view more event details and to register/book the event tickets.
![Event Details](src/main/resources/org/example/ics108project/project-images/readme-images/Screenshot 2024-05-12 at 23.47.38.png)
![Event Details](src/main/resources/org/example/ics108project/project-images/readme-images/Screenshot 2024-05-12 at 23.48.47.png)
10. **Book Tickets**: Click on "Book Your Ticket" and enter User name and User email ID (Gmail) to book tickets.
![Book Tickets](src/main/resources/org/example/ics108project/project-images/readme-images/Screenshot 2024-05-12 at 23.49.19.png)
![Book Tickets](src/main/resources/org/example/ics108project/project-images/readme-images/Screenshot 2024-05-12 at 23.50.15.png)
11. **View Your Events**: After booking all the desired events, switch to the "My Events" page/scene.
![My Events](src/main/resources/org/example/ics108project/project-images/readme-images/Screenshot 2024-05-12 at 23.51.06.png)
![My Events](src/main/resources/org/example/ics108project/project-images/readme-images/Screenshot 2024-05-12 at 23.53.03.png)
