package gui.controllers;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.CalendarEvent;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.model.Entry;
import com.calendarfx.view.WeekView;
import com.calendarfx.view.page.WeekPage;
import core.Meeting;
import core.ScheduleFacade;
import core.SessionFacade;
import javafx.event.EventHandler;
import javafx.fxml.FXML;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;


public class UserHomeController {

    @FXML
    private WeekPage weekPage;

    private Calendar calendar = new Calendar("Calendar");
    private Collection<Entry> entries;
    //user = getUsername;
   //listEntries = facade.checkSchedule(user);

    private static ScheduleFacade facade = new ScheduleFacade();
    private static SessionFacade sfacade = new SessionFacade();

    private Collection<Meeting> meetings;

    private void handle(CalendarEvent calendarEvent){
        if (calendarEvent.isEntryAdded()){
            entries.add(calendarEvent.getEntry());
        }
        else if (calendarEvent.isEntryRemoved()){
            entries.remove(calendarEvent.getEntry());
        }
    }

    @FXML
    private void initialize(){
        calendar.setReadOnly(true);
        EventHandler<CalendarEvent> handler = calendarEvent -> handle(calendarEvent);
        calendar.setStyle(Calendar.Style.STYLE1);

        Collection<Meeting> meetings = facade.checkSchedule(SessionFacade.getConnectedUser().getUserName());
        int i = 0;
        for(Meeting m : meetings){
            //TODO : Résoudre pb de références vides à un topic dans la BDD + afficher le nom et plus cours + i
            //Entry<String> e = new Entry<>(m.getMeetingTopic().getNameTopic());
            Entry<String> e = new Entry<>("Cours " + i);
            e.changeStartDate(m.getDateBegin());
            e.changeEndDate(m.getDateEnd());
            e.changeStartTime(m.getHourBegin());
            e.changeEndTime(m.getHourEnd());

            calendar.addEntry(e);
            i++;
        }

        CalendarSource calendarSource = new CalendarSource("Week calendar");
        calendarSource.getCalendars().addAll(calendar);
        weekPage.getCalendarSources().addAll(calendarSource);

    }

}