package gui.controllers;

import core.InvitationFacade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

import java.util.ArrayList;
import java.util.Collection;

public class InvitePeopleController {

    @FXML
    private ListView<HBoxCell> listViewGroup;
    @FXML
    private ListView<HBoxCell> listViewPeople;

    private static final InvitationFacade invitationFacade = new InvitationFacade();

    int idMeeting = SeeInvitedPeopleController.idMeeting;

    ObservableList<HBoxCell> listPeople =  FXCollections.observableArrayList();
    ObservableList<HBoxCell> listGroups =  FXCollections.observableArrayList();



    private class HBoxCell extends HBox {

        Label label = new Label();
        Button inviteButton = new Button("Invite");

        HBoxCell(String labelText, ListView listView) {

            super();

            label.setText(labelText);
            label.setMaxWidth(Double.MAX_VALUE);
            HBox.setHgrow(label, Priority.ALWAYS);

            inviteButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    if (listView.equals(listViewPeople)){
                        invitationFacade.createInvitationForUser(labelText, 0, idMeeting);
                    }
                    else if (listView.equals(listViewGroup)){
                        invitationFacade.createInvitationForGroup(labelText, 0, idMeeting);
                        refresh(listViewPeople);
                    }
                }
            });
            this.getChildren().addAll(label, inviteButton);
        }


    }

    @FXML
    private void initialize(){

        Collection<String> invitedUsers = invitationFacade.getAllInvitedUsers(idMeeting);
        Collection<String> users = invitationFacade.getAllUsers();
        //TODO : gérer les groupes invités (il ne faut pas que quelqu'un soit invité 2 fois si il appartient à un groupe)
        Collection<String> invitedGroups = invitationFacade.getAllInvitedGroups(idMeeting);
        Collection<String>groups = invitationFacade.getAllGroups();

        ArrayList<String> listUsersWithoutInvitedUser = new ArrayList<>();
        listUsersWithoutInvitedUser.addAll(users);

        ArrayList<String> listGroupsWithoutInvitedGroup = new ArrayList<>();
        listGroupsWithoutInvitedGroup.addAll(groups);


        for (String u : users){
            if (invitedUsers.contains(u)){
                listUsersWithoutInvitedUser.remove(u);
            }
        }
        for (String nameUSer : listUsersWithoutInvitedUser ){
            HBoxCell hbc = new HBoxCell(nameUSer, listViewPeople);
            listPeople.add(hbc);
        }
        for (String g : groups){
            if (invitedGroups.contains(g)){
                listGroupsWithoutInvitedGroup.remove(g);
            }
        }
        for (String nameGroup : listGroupsWithoutInvitedGroup ){
            HBoxCell hbc = new HBoxCell(nameGroup, listViewGroup);
            listGroups.add(hbc);
        }


        listViewPeople.setItems(listPeople);
        listViewGroup.setItems(listGroups);


    }


    private void loadListView(ListView listView, ObservableList oblist){

    }

    private void refresh(ListView listView){
        Collection<String> invitedUsers = invitationFacade.getAllInvitedUsers(idMeeting);
        Collection<String> users = invitationFacade.getAllUsers();

        ArrayList<String> listUsersWithoutInvitedUser = new ArrayList<>();
        listUsersWithoutInvitedUser.addAll(users);

        for (String u : users){
            if (invitedUsers.contains(u)){
                listUsersWithoutInvitedUser.remove(u);
            }
        }
        for (String nameUSer : listUsersWithoutInvitedUser ){
            HBoxCell hbc = new HBoxCell(nameUSer, listView);
            listPeople.add(hbc);
        }
        listView.setItems(listPeople);
    }


    public void research(ActionEvent actionEvent) {

    }
}
