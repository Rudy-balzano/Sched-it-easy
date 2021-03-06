package gui.controllers;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.CalendarEvent;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.model.Entry;
import com.calendarfx.view.page.WeekPage;
import core.*;
import gui.Main;
import gui.roots.Roots;
import gui.views.popover.MyCustomPopOverHomeView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.time.LocalDate;
import java.util.*;

/**
 * Class controller to manage the autoSchedule
 * @version 1.0
 */
public class ManagerAutoScheduleController {

    @FXML
    private WeekPage weekPage;
    /**
     * Calendar
     */
    Calendar calendar = new Calendar("Calendar");
    /**
     * reservationFacade
     */
    private final ReservationFacade facade = new ReservationFacade();
    /**
     * The autoSchedule
     */
    private Collection<Meeting> autosched = ManagerCreateAutoScheduleController.autosched;

    /**
     * Function used to initialize
     */
    @FXML
    private void initialize() {


        calendar.setReadOnly(true);
        weekPage.setContextMenuCallback(null);
        weekPage.setEntryFactory(param -> null);

        calendar.setStyle(Calendar.Style.STYLE1);

        for (Meeting m : autosched) {
            Entry<String> e = new Entry<>(m.getMeetingTopic().getNameTopic());
            e.changeStartDate(m.getDateBegin());
            e.changeEndDate(m.getDateEnd());
            e.changeStartTime(m.getHourBegin());
            e.changeEndTime(m.getHourEnd());

            calendar.addEntry(e);
        }

        CalendarSource calendarSource = new CalendarSource("Week calendar");
        calendarSource.getCalendars().addAll(calendar);
        weekPage.getCalendarSources().addAll(calendarSource);

    }

    /**
     * Function used to cancel
     * @throws IOException
     */
    public void handleCancel() throws IOException {
        Main.scheditWindow.setScene(new Scene(FXMLLoader.load(getClass().getResource(Roots.managerHomeRoot))));
    }

    /**
     * function used to handle new entry
     */
    public void handleNewOne() throws Exception {

        calendar.clear();

        LocalDate dD = facade.getdD();
        LocalDate dF = facade.getdF();
        HashMap<String, Integer> matieres = facade.getMatiere();

        autosched = facade.autoSchedule(matieres, dD, dF);


        for (Meeting m : autosched) {
            Entry<String> e = new Entry<>(m.getMeetingTopic().getNameTopic());
            e.changeStartDate(m.getDateBegin());
            e.changeEndDate(m.getDateEnd());
            e.changeStartTime(m.getHourBegin());
            e.changeEndTime(m.getHourEnd());

            calendar.addEntry(e);
        }
        CalendarSource calendarSource = new CalendarSource("Week calendar");
        calendarSource.getCalendars().addAll(calendar);
        weekPage.getCalendarSources().addAll(calendarSource);
    }

    /**
     * Function used to validate the creation of the autoSchedule
     * @throws IOException
     */
    public void handleValidation() throws IOException{
        for(Meeting m : autosched){
            facade.createMeeting(m.getDateBegin(),m.getHourBegin(),m.getDateEnd(),m.getHourEnd(),m.getMeetingTopic().getNameTopic());
        }
        Main.scheditWindow.setScene(new Scene(FXMLLoader.load(getClass().getResource(Roots.managerHomeRoot))));
    }
}
