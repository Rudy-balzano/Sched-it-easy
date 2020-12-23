package gui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class CreateMeetingController {


    @FXML
    private TextField topic;

    @FXML
    private DatePicker date;

    @FXML
    private ChoiceBox hour_meeting;

    @FXML
    private ChoiceBox minute_meeting;

    @FXML
    private ChoiceBox hour_duration;

    @FXML
    private ChoiceBox minute_duration;


    public void handleCreateMeeting(ActionEvent actionEvent) {
    }

    public void handleCancel(ActionEvent actionEvent) {
    }

    public void handleBookRoom(ActionEvent actionEvent) {
    }
}
