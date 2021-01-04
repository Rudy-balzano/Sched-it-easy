package gui.controllers;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.CalendarEvent;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.model.Entry;
import com.calendarfx.view.page.DayPage;
import core.Meeting;
import core.ReservationFacade;
import gui.Main;
import gui.roots.Roots;
import gui.views.popover.MyCustomPopOverContentNode;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class CreateMeetingController {


    @FXML
    private DayPage dayPage;

    Calendar calendar = new Calendar("Calendar");

    ArrayList<Entry> listEntries = new ArrayList<>();

    public static Meeting meeting;

    private static ReservationFacade reservationfacade = new ReservationFacade();




    @FXML
    private void initialize(){

        dayPage.setEntryDetailsPopOverContentCallback(param -> new MyCustomPopOverContentNode());

        EventHandler<CalendarEvent> handler = evt -> handle(evt);
        calendar.addEventHandler(handler);

        calendar.setStyle(Calendar.Style.STYLE1);

        CalendarSource myCalendarSource = new CalendarSource("My Calendar");
        myCalendarSource.getCalendars().add(calendar);

        dayPage.getCalendarSources().add(0,myCalendarSource);



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


    private void handle(CalendarEvent evt) {
        if (evt.isEntryAdded()){
            listEntries.add(evt.getEntry());
        }
        else if (evt.isEntryRemoved()){
            listEntries.remove(evt.getEntry());
        }

        System.out.println(evt.getEntry().getDuration());
        System.out.println(evt.getEntry().getStartDate());
        System.out.println(evt.getEntry().getStartTime());

    }






    public void handleCreateMeeting(ActionEvent actionEvent) {
        createMeeting();
    }

    public void handleCancel(ActionEvent actionEvent) {
    }

    public void handleBookRoom(ActionEvent actionEvent) throws IOException {

        createMeeting();
//        meeting = reservationfacade.findMeetingById();
        Main.scheditWindow.setScene(new Scene(FXMLLoader.load(getClass().getResource(Roots.bookRoomRoot))));
    }

    private void createMeeting(){
        Boolean createMeeting = false;

        for ( int i = 0 ; i < listEntries.size() ; i++) {

            createMeeting = reservationfacade.createMeeting(listEntries.get(i).getStartDate(), listEntries.get(i).getStartTime(), listEntries.get(i).getEndDate(), listEntries.get(i).getEndTime(), " ");

            if (createMeeting){
                System.out.println("Meeting inserted !");
            }
            else {
                System.out.println("Meeting not inserted ...");
            }
        }
    }
}
