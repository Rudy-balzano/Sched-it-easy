package gui.controllers;

import core.ManagerFacade;
import core.Meeting;
import core.RoomTopicFacade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Window;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class MeetingValidationController {

    private static final ManagerFacade facade = new ManagerFacade();
    private HashMap<String,Integer> waitingMeetings = facade.getAllWaitingMeetings();

    @FXML
    private ListView<HBoxCell>  listwaitingMeetings;
    @FXML
    private Button validateMeetingButton;



    private class HBoxCell extends HBox {

        Label label = new Label();
        Button button = new Button("Validate Meeting");
        Button button2 = new Button("Infos");

        HBoxCell(String labelText) {

            super();

            label.setText(labelText);
            label.setMaxWidth(Double.MAX_VALUE);
            HBox.setHgrow(label, Priority.ALWAYS);

            this.getChildren().addAll(label, button, button2);
            button.setOnAction(actionEvent -> {
                Integer id = waitingMeetings.get(label.getText());
                facade.validationMeeting(id);
                refresh();
            });
            button2.setOnAction(actionEvent -> {

            });

        }}
    private void refresh(){

        ObservableList<HBoxCell> itemsMe = FXCollections.observableArrayList();

        for (String name : waitingMeetings.keySet()){
            HBoxCell hbc = new HBoxCell(name);
            itemsMe.add(hbc);
        }

        listwaitingMeetings.setItems(itemsMe);
    }
        public void initialize(){



            ObservableList<HBoxCell> itemsMe = FXCollections.observableArrayList();
            for (String name : waitingMeetings.keySet()) {
                //Consider using iterator
                HBoxCell hbc = new HBoxCell(name);
                itemsMe.add(hbc);
            }
            listwaitingMeetings.setItems(itemsMe);
        }


}

