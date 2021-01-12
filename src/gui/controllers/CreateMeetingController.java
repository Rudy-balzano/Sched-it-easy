package gui.controllers;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.CalendarEvent;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.model.Entry;
import com.calendarfx.view.page.DayPage;
import core.Meeting;
import core.ReservationFacade;
import core.SessionFacade;
import gui.Main;
import gui.roots.Roots;
import gui.views.popover.MyCustomPopOverCreateMeetingView;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Window;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * Class Controller of create meeting
 * @version 1.0
 */
public class CreateMeetingController implements AlertShower {


    @FXML
    private DayPage dayPage;
    /**
     * calendar
     */
    Calendar calendar = new Calendar("Calendar");

    /**
     * meeting
     */
    public static Meeting meeting;
    /**
     * reservationFacade
     */
    private static ReservationFacade reservationfacade = new ReservationFacade();


    private Entry uniqueMeeting = null;

    @FXML
    public Button createMeetingButton;

    @FXML
    public Button bookRoomButton;

    /**
     *
     */
    @FXML
    private void initialize(){

        createMeetingButton.setDisable(true);
        bookRoomButton.setDisable(true);

        EventHandler<CalendarEvent> handler = evt -> handle(evt);
        calendar.addEventHandler(handler);

        calendar.setStyle(Calendar.Style.STYLE1);

        CalendarSource myCalendarSource = new CalendarSource("My Calendar");
        myCalendarSource.getCalendars().add(calendar);

        dayPage.getCalendarSources().add(0,myCalendarSource);

        dayPage.setEntryDetailsPopOverContentCallback(param -> new MyCustomPopOverCreateMeetingView(param.getEntry()));



        dayPage.setRequestedTime(LocalTime.now());


        Thread updateTimeThread = new Thread("Calendar: Update Time Thread") {
            @Override
            public void run() {
                while (true) {
                    Platform.runLater(() -> {
                        dayPage.setToday(LocalDate.now());
                        dayPage.setTime(LocalTime.now());
                    });

                    try {
                        // update every 10 seconds
                        sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            };
        };

        updateTimeThread.setPriority(Thread.MIN_PRIORITY);
        updateTimeThread.setDaemon(true);
        updateTimeThread.start();
    }

    /**
     *
     * @param evt
     */
    private void handle(CalendarEvent evt) {
        if (evt.isEntryAdded()){
            uniqueMeeting = evt.getEntry();
            evt.getEntry().setTitle("Add the topic !");
            dayPage.setEntryFactory(param -> null);
            createMeetingButton.setDisable(false);
            bookRoomButton.setDisable(false);

        }
        else if (evt.isEntryRemoved()) {
            uniqueMeeting = null;
            createMeetingButton.setDisable(true);
            bookRoomButton.setDisable(true);
        }


    }


    /**
     * Function used to create a meeting and handle if you choose to cancel
     * @throws IOException
     */
    public void handleCreateMeeting() throws IOException {

        Window owner = createMeetingButton.getScene().getWindow();

        if (!uniqueMeeting.getTitle().equals("Add the topic !")){
            saveMeeting();
            handleCancel();
        }
        else {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please Enter a topic for your meeting");
            return;
        }
    }

    /**
     * Function used to handle the case if you want to cancel
     * @throws IOException
     */
    public void handleCancel() throws IOException {
        if(SessionFacade.getConnectedUser().getIsManager()){
            Main.scheditWindow.setScene(new Scene(FXMLLoader.load(getClass().getResource(Roots.managerHomeRoot))));
        }
        else{
            Main.scheditWindow.setScene(new Scene(FXMLLoader.load(getClass().getResource(Roots.userHomeRoot))));

        }
    }

    /**
     * Function used to handle if you want to book a room
     * @param actionEvent
     * @throws IOException
     */
    public void handleBookRoom(ActionEvent actionEvent) throws IOException {

        int idMeeting=-1;

        Window owner = createMeetingButton.getScene().getWindow();

        if (!uniqueMeeting.getTitle().equals("Add the topic !")){
            idMeeting = reservationfacade.createMeetingAndGetId(uniqueMeeting.getStartDate(), uniqueMeeting.getStartTime(), uniqueMeeting.getEndDate(), uniqueMeeting.getEndTime(), uniqueMeeting.getTitle());
            if (idMeeting!=-1){
                if (SessionFacade.getConnectedUser().getIsManager()){
                    meeting = reservationfacade.findMeetingById(idMeeting);
                }else {
                    meeting = reservationfacade.findWaitingMeetingById(idMeeting);
                }
                System.out.println("Meeting inserted !");
                Main.scheditWindow.setScene(new Scene(FXMLLoader.load(getClass().getResource(Roots.bookRoomRoot))));
            }
            else {
                System.out.println("Meeting not inserted ...");
            }
        }
        else {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please Enter a topic for your meeting");
            return;
        }

    }

    /**
     * Function used to create a meeting
     */
    private void saveMeeting(){
        Boolean createMeeting = false;

        createMeeting = reservationfacade.createMeeting(uniqueMeeting.getStartDate(), uniqueMeeting.getStartTime(), uniqueMeeting.getEndDate(), uniqueMeeting.getEndTime(), uniqueMeeting.getTitle());

        if (createMeeting){
            System.out.println("Meeting inserted !");
        }
        else {
            System.out.println("Meeting not inserted ...");
        }
    }
}
