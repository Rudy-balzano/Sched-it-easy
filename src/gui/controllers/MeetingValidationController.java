package gui.controllers;

import core.ManagerFacade;
import core.Meeting;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.util.HashMap;

public class MeetingValidationController {

    private static final ManagerFacade facade = new ManagerFacade();

    private HashMap<Integer,String> waitingMeetings = facade.getAllWaitingMeetings();

    @FXML
    private ListView<HBoxCell>  listwaitingMeetings;


    private class HBoxCell extends HBox {

        Label label = new Label();
        Button button = new Button("Validate");
        Button button1 = new Button("Decline");
        Button button2 = new Button("Infos");

        HBoxCell(String labelText) {

            super();


            label.setText(labelText.split(" ")[0]);
            label.setMaxWidth(Double.MAX_VALUE);
            HBox.setHgrow(label, Priority.ALWAYS);

            Integer id = Integer.parseInt(labelText.split(" ")[1]);

            System.out.println(id);

            this.getChildren().addAll(label, button, button1, button2);
            button.setOnAction(actionEvent -> {
                facade.validationMeeting(id);
                refresh();
            });

            button2.setOnAction((ActionEvent actionEvent) -> displayPopupMeetingInfo(facade.getWaitingMeetingById(id)));
            button1.setOnAction(actionEvent -> {
                facade.declineMeeting(id);
                refresh();
            });

        }
    }

    private void displayPopupMeetingInfo(Meeting m){
        Stage popup = new Stage();
        popup.initModality(Modality.APPLICATION_MODAL);
        popup.setTitle("Meeting information");

        Label dateD = new Label("Begins : " + m.getDateBegin().toString()+"   "+m.getHourBegin());
        Label dateF = new Label("Ends : " + m.getDateEnd().toString()+"   "+m.getHourEnd());
        Label user = new Label("Created by  : " + m.getClientMeeting());

        Button b = new Button("Close");
        b.setOnAction(actionEvent -> popup.close());
        VBox layout = new VBox(10);
        layout.getChildren().addAll(dateD,dateF,user,b);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout,200,150);
        popup.setScene(scene);
        popup.showAndWait();

    }

    private void refresh(){
        HashMap<Integer,String> meetingsRefreshed = facade.getAllWaitingMeetings();

        ObservableList<HBoxCell> itemsMe = FXCollections.observableArrayList();

        for (Integer id : meetingsRefreshed.keySet()){
            HBoxCell hbc = new HBoxCell(meetingsRefreshed.get(id));
            itemsMe.add(hbc);
        }

        listwaitingMeetings.setItems(itemsMe);
    }
        public void initialize(){

            ObservableList<HBoxCell> itemsMe = FXCollections.observableArrayList();
            for (Integer id : waitingMeetings.keySet()) {
                HBoxCell hbc = new HBoxCell(waitingMeetings.get(id));
                itemsMe.add(hbc);
            }
            listwaitingMeetings.setItems(itemsMe);
        }


}

