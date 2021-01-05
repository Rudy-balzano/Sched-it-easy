package gui.controllers;

import core.Invitation;
import core.InvitationFacade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

import java.util.ArrayList;

public class InvitationController {
    private static final InvitationFacade facade = new InvitationFacade();
    private ArrayList<Invitation> waitingInvitation = facade.getAllInvitation();

    @FXML
    private ListView<HBoxCell> listwaitingsInvitation;
    private class HBoxCell extends HBox {

        Label label = new Label();
        Button button = new Button("Present");
        Button button1 = new Button("Absent");

        HBoxCell(Invitation invit) {

            super();
            String inv = invit.getMeetingInvitation().getClientMeeting() + " " + invit.getMeetingInvitation().getDateBegin() + " " +invit.getMeetingInvitation().getDateEnd() + " from " +invit.getMeetingInvitation().getHourBegin() + " to " + invit.getMeetingInvitation().getHourEnd() + " about " + invit.getMeetingInvitation().getMeetingTopic()/*.getNameTopic()*/;
            //TODO : trouver un moyen de mieux présenter l'affichage ?
            //TODO : décommenter le .getNameTopic de inv = quand on aura mis des invitations avec des topics dans la BDD

            label.setText(inv);
            label.setMaxWidth(Double.MAX_VALUE);
            HBox.setHgrow(label, Priority.ALWAYS);


            this.getChildren().addAll(label,button, button1);

            button.setOnAction(actionEvent -> {
                facade.acceptInvitation(invit.getMeetingInvitation().getClientMeeting(), invit.getMeetingInvitation().getId());
                refresh();
            });

            button1.setOnAction(actionEvent -> {
                facade.declineInvitation(invit.getMeetingInvitation().getClientMeeting(), invit.getMeetingInvitation().getId());
                refresh();
            });

        }
    }

    private void refresh(){
        ArrayList<Invitation> invitationsRefreshed = facade.getAllInvitation();

        ObservableList<HBoxCell> itemsInv = FXCollections.observableArrayList();

        for (Invitation i : invitationsRefreshed){
            int id = i.getMeetingInvitation().getId();
            HBoxCell hbc = new HBoxCell(invitationsRefreshed.get(id));
            itemsInv.add(hbc);
        }

        listwaitingsInvitation.setItems(itemsInv);
    }
    public void initialize(){

        ObservableList<HBoxCell> itemsInv = FXCollections.observableArrayList();
        for (Invitation i : waitingInvitation) {
            System.out.println(i.getInvitedUser().getUserName());
            HBoxCell hbc = new HBoxCell(i);
            itemsInv.add(hbc);
        }
        listwaitingsInvitation.setItems(itemsInv);
    }

}
