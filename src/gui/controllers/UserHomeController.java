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
import gui.views.popover.MyCustomPopOverHomeView;
import javafx.fxml.FXML;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Class controller for the user home
 * @version 1.0
 */
public class UserHomeController {

    @FXML
    private WeekPage weekPage;
    /**
     * Calendar
     */
    Calendar calendar = new Calendar("Calendar");
    /**
     * Schedule facade
     */
    private static ScheduleFacade facade = new ScheduleFacade();


    private void handle(CalendarEvent evt) {
    }

    /**
     * Function used to initialize the view by displaying the calendar, and setting the ui readonly
     */
    @FXML
    private void initialize(){

        weekPage.setEntryDetailsPopOverContentCallback(param -> new MyCustomPopOverHomeView(param.getEntry()));

        //Setting the ui readonly
        calendar.setReadOnly(true);
        weekPage.setContextMenuCallback(null);
        weekPage.setEntryFactory(param -> null);

        calendar.setStyle(Calendar.Style.STYLE1);

        Collection<Meeting> meetings = facade.checkSchedule(SessionFacade.getConnectedUser().getUserName());

        for(Meeting m : meetings){
            Entry<String> e = new Entry<>(m.getMeetingTopic().getNameTopic());
            e.changeStartDate(m.getDateBegin());
            e.changeEndDate(m.getDateEnd());
            e.changeStartTime(m.getHourBegin());
            e.changeEndTime(m.getHourEnd());
            e.setId(String.valueOf(m.getId()));

            calendar.addEntry(e);
        }
        /*
        ArrayList<Invitation> inv = facade.checkInvitation(SessionFacade.getConnectedUser().getUserName());

        for(Invitation in : inv){
            //TODO : Résoudre pb de références vides à un topic dans la BDD + afficher le nom et plus cours + i
            Entry<String> e = new Entry<>(in.getMeetingInvitation().getMeetingTopic().getNameTopic());
            e.changeStartDate(in.getMeetingInvitation().getDateBegin());
            e.changeEndDate(in.getMeetingInvitation().getDateEnd());
            e.changeStartTime(in.getMeetingInvitation().getHourBegin());
            e.changeEndTime(in.getMeetingInvitation().getHourEnd());

            calendar.addEntry(e);
        }
       
         */

        CalendarSource calendarSource = new CalendarSource("Week calendar");
        calendarSource.getCalendars().addAll(calendar);
        weekPage.getCalendarSources().addAll(calendarSource);


    }

}