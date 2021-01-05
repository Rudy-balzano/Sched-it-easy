package gui.controllers;
import com.calendarfx.model.Calendar;
import com.calendarfx.model.CalendarEvent;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.model.Entry;
import com.calendarfx.view.page.WeekPage;
import core.ReservationFacade;
import core.ScheduleFacade;
import gui.views.popover.MyCustomPopOverContentNode;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;


public class UserHomeController {

    @FXML
    private WeekPage weekPage;
    Calendar calendar = new Calendar("Calendar");
    private static ScheduleFacade facade = new ScheduleFacade();
    ArrayList<Entry> listEntries = new ArrayList<>();
    //user = getUsername;
   // listEntries = facade.checkSchedule(user);
}