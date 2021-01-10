package gui.controllers;

import core.Equipment;
import core.Invitation;
import core.InvitationFacade;
import core.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Class controller for an invitation
 * @author
 * @version 1.0
 */
public class InvitationController {
    /**
     * Invitation facade
     */
    private static final InvitationFacade facade = new InvitationFacade();
    /**
     * List of every waitings invitations
     */
    private ArrayList<Invitation> waitingInvitation = facade.getAllInvitation();
    /**
     * List of bills
     */
    private Collection<String> notifFactures = new ArrayList<>();

    @FXML
    private ListView<HBoxCell> listwaitingsInvitation;

    /**
     * Class to the listView
     */
    private class HBoxCell extends HBox {

        Label label = new Label();
        Button button = new Button("Present");
        Button button1 = new Button("Absent");
        Button button2 = new Button("Infos");
        Button button3 = new Button("Get in PDF");

        /**
         * Function used for the ListView, to display the notifications
         * @param str
         */
        HBoxCell(String str) {

            super();
            label.setText(str);
            label.setMaxWidth(Double.MAX_VALUE);
            HBox.setHgrow(label, Priority.ALWAYS);

            if(!str.endsWith("euros.")) {

                this.getChildren().addAll(label, button, button1, button2);

                String id = label.getText().split(": ")[1];
                int idMeeting = Integer.parseInt(id);

                Invitation invit = facade.findInvitation(idMeeting);

                button.setOnAction(actionEvent -> {
                    facade.acceptInvitation(invit.getInvitedUser2(), invit.getMeetingInvitation().getId());
                    refresh();
                });

                button1.setOnAction(actionEvent -> {
                    facade.declineInvitation(invit.getInvitedUser2(), invit.getMeetingInvitation().getId());
                    refresh();
                });
                button2.setOnAction(actionEvent -> {
                    displayPopupInfo(invit);
                });
            } else {
                this.getChildren().addAll(label,button3);

                String id = label.getText().split(" ")[5];
                int idMeeting = Integer.parseInt(id);

                button3.setOnAction(actionEvent -> {
                    Collection<Equipment> equipments = facade.getRentedEquipment(idMeeting);
                    try {
                        facade.facture(equipments,idMeeting);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }

        }
    }

    private void displayPopupInfo(Invitation invit){
        Stage popup = new Stage();
        popup.initModality(Modality.APPLICATION_MODAL);
        popup.setTitle("Invitation information");

        Label meeting = new Label("The meeting :");
        Label dD = new Label("Start the : " + invit.getMeetingInvitation().getDateBegin() +"\n at : " + invit.getMeetingInvitation().getHourBegin());
        Label dE = new Label("And end at : " + invit.getMeetingInvitation().getHourEnd());

        Button b = new Button("Close");
        b.setOnAction(actionEvent -> popup.close());
        VBox layout = new VBox(10);
        layout.getChildren().addAll(meeting,dD,dE,b);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout,200,150);
        popup.setScene(scene);
        popup.showAndWait();

    }

    /**
     * Function used to refresh the listView
     */
    private void refresh(){
        ArrayList<Invitation> invitationsRefreshed = facade.getAllInvitation();

        ObservableList<HBoxCell> itemsInv = FXCollections.observableArrayList();

        for (Invitation i : invitationsRefreshed){
            String str = "Invitation from "+i.getMeetingInvitation().getClientMeeting() + " for the meeting n° : "+i.getMeetingInvitation().getId();
            HBoxCell hbc = new HBoxCell(str);
            itemsInv.add(hbc);
        }
        Collection<Integer> meetingsWithRoom = facade.allMeetingsWithRoom();

        for(Integer i : meetingsWithRoom){
            String str = facade.notifFacture(facade.getRentedEquipment(i),i);
            HBoxCell hbc = new HBoxCell(str);
            itemsInv.add(hbc);
        }

        listwaitingsInvitation.setItems(itemsInv);
    }

    /**
     * Function used to initialize the listView with every waitings invitations
     */
    public void initialize(){

        ObservableList<HBoxCell> itemsInv = FXCollections.observableArrayList();

        for (Invitation i : waitingInvitation) {
            String str = "Invitation from "+i.getMeetingInvitation().getClientMeeting() + " for the meeting n° : "+i.getMeetingInvitation().getId();
            HBoxCell hbc = new HBoxCell(str);
            itemsInv.add(hbc);
        }

        Collection<Integer> meetingsWithRoom = facade.allMeetingsWithRoom();
        System.out.println(meetingsWithRoom);

        for(Integer i : meetingsWithRoom){
           String str = facade.notifFacture(facade.getRentedEquipment(i),i);
            HBoxCell hbc = new HBoxCell(str);
            itemsInv.add(hbc);
        }
        listwaitingsInvitation.setItems(itemsInv);
    }

}
