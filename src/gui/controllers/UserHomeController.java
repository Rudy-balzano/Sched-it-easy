package gui.controllers;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.CalendarEvent;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.model.Entry;
import com.calendarfx.view.page.WeekPage;
import core.Invitation;
import core.Meeting;
import core.ScheduleFacade;
import core.SessionFacade;
import javafx.event.EventHandler;
import javafx.fxml.FXML;

import java.util.ArrayList;
import java.util.Collection;


public class UserHomeController {

    @FXML
    private WeekPage weekPage;

    Calendar calendar = new Calendar("Calendar");

    private static ScheduleFacade facade = new ScheduleFacade();

    @FXML
    private void initialize(){
        //Setting the ui readonly
        calendar.setReadOnly(true);
        weekPage.setContextMenuCallback(null);
        weekPage.setEntryFactory(param -> null);

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
        ArrayList<Invitation> inv = facade.checkInvitation(SessionFacade.getConnectedUser().getUserName());
        int j = 0;
        for(Invitation in : inv){
            //TODO : Résoudre pb de références vides à un topic dans la BDD + afficher le nom et plus cours + i
            //Entry<String> e = new Entry<>(m.getMeetingTopic().getNameTopic());
            Entry<String> e = new Entry<>("Cours " + j);
            e.changeStartDate(in.getMeetingInvitation().getDateBegin());
            e.changeEndDate(in.getMeetingInvitation().getDateEnd());
            e.changeStartTime(in.getMeetingInvitation().getHourBegin());
            e.changeEndTime(in.getMeetingInvitation().getHourEnd());

            calendar.addEntry(e);
            j++;
        }

        CalendarSource calendarSource = new CalendarSource("Week calendar");
        calendarSource.getCalendars().addAll(calendar);
        weekPage.getCalendarSources().addAll(calendarSource);


    }

}