package gui.controllers;

import core.Equipment;
import core.InvitationFacade;
import core.Topic;
import core.User;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.Collection;

public class InvitePeopleController {

    @FXML
    private ListView<HBoxCell> listViewGroup;
    @FXML
    private ListView<HBoxCell> listViewPeople;

    private static final InvitationFacade invitationFacade = new InvitationFacade();

    int idMeeting = SeeInvitedPeopleController.idMeeting;



    private class HBoxCell extends HBox {

        Label label = new Label();
        Button inviteButton = new Button("Invite");

        HBoxCell(String labelText) {

            super();

            label.setText(labelText);
            label.setMaxWidth(Double.MAX_VALUE);
            HBox.setHgrow(label, Priority.ALWAYS);

            inviteButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    invitationFacade.createInvitation(labelText, 0, idMeeting);
                }
            });
            this.getChildren().addAll(label, inviteButton);
        }


    }

    @FXML
    private void initialize(){
        ObservableList<HBoxCell> listPeople =  FXCollections.observableArrayList();
        ObservableList<HBoxCell> listGroup =  FXCollections.observableArrayList();

        Collection<String> invitedUsers = invitationFacade.getAllInvitedUsers(idMeeting);
        Collection<String> users = invitationFacade.getAllUsers();
        //TODO : gérer les groupes invités (il ne faut pas que quelqu'un soit invité 2 fois si il appartient à un groupe)
//        Collection<String> invitedGroups = invitationFacade.getAllGroups();
        Collection<String>groups = invitationFacade.getAllGroups();

        ArrayList<String> listUsersWithoutInvitedUser = new ArrayList<>();
        listUsersWithoutInvitedUser.addAll(users);

        for (String u : users){
            if (invitedUsers.contains(u)){
                listUsersWithoutInvitedUser.remove(u);
            }
        }

        for (String name : listUsersWithoutInvitedUser ){
            HBoxCell hbc = new HBoxCell(name);
            listPeople.add(hbc);
        }

        listViewPeople.setItems(listPeople);
        listViewGroup.setItems(listGroup);


    }


    public void research(ActionEvent actionEvent) {

    }
}
