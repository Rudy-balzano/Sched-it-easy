package gui.controllers;

import core.ReservationFacade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

public class CreateMeetingController {



    @FXML
    private TextField topic;

    @FXML
    private DatePicker date;

    @FXML
    private ChoiceBox<Integer> hour_meeting;

    @FXML
    private ChoiceBox<Integer> minute_meeting;

    @FXML
    private ChoiceBox<Integer> hour_duration;

    @FXML
    private ChoiceBox<Integer> minute_duration;


    public void handleCreateMeeting(ActionEvent actionEvent) {
        ReservationFacade reservationfacade = new ReservationFacade();

        String topicName = topic.getText();
        LocalDate dateMeeting = date.getValue();
        int Hour_meeting = hour_meeting.getValue();
        int Minute_meeting = minute_meeting.getValue();
        int Hour_duration = hour_duration.getValue();
        int Minute_duration = minute_duration.getValue();

        String timeMeeting = Hour_meeting+":"+Minute_meeting;
        String duration = Hour_duration+":"+Minute_duration;

        Boolean createMeeting = false;

        createMeeting = reservationfacade.createMeeting(topicName, dateMeeting, timeMeeting, duration);

        if (createMeeting){
            System.out.println("Meeting inserted !");
        }
        else {
            System.out.println("Meeting not inserted ...");
        }


    }

    public void handleCancel(ActionEvent actionEvent) {
    }

    public void handleBookRoom(ActionEvent actionEvent) {
    }
}
