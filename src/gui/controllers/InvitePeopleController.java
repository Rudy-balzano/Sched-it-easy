package gui.controllers;

import core.InvitationFacade;
import core.SessionFacade;
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

/**
 * Class controller to invite People to a meeting
 * @author
 * @version 1.0
 */
public class InvitePeopleController {

    @FXML
    private ListView<HBoxCell> listViewGroup;
    @FXML
    private ListView<HBoxCell> listViewPeople;
    /**
     * invitationFacade
     */
    private static final InvitationFacade invitationFacade = new InvitationFacade();
    /**
     * Id of a meeting
     */
    int idMeeting = SeeInvitedPeopleController.idMeeting;



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
                        refresh();
                    }
                }
            });
            this.getChildren().addAll(label, inviteButton);
        }


    }

    /**
     * Function used to initialize the listView
     */
    @FXML
    private void initialize(){

        ObservableList<HBoxCell> listPeople =  FXCollections.observableArrayList();
        ObservableList<HBoxCell> listGroups =  FXCollections.observableArrayList();

        Collection<String> invitedUsers = invitationFacade.getAllInvitedUsers(idMeeting);
        Collection<String> users = invitationFacade.getAllUsers();
        //TODO : gérer les groupes invités (il ne faut pas que quelqu'un soit invité 2 fois si il appartient à un groupe)
        Collection<String> invitedGroups = invitationFacade.getAllInvitedGroups(idMeeting);
        Collection<String>groups = invitationFacade.getAllGroups();

        ArrayList<String> listUsersWithoutInvitedUser = new ArrayList<>();
        listUsersWithoutInvitedUser.addAll(users);
        //Je m'enlève de la liste des users à inviter
        listUsersWithoutInvitedUser.remove(SessionFacade.getConnectedUser().getUserName());

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

    /**
     * Function used to refresh the listView
     * @param listView
     */
    private void refresh(){
        ObservableList<HBoxCell> listPeople =  FXCollections.observableArrayList();
        Collection<String> invitedUsers = invitationFacade.getAllInvitedUsers(idMeeting);
        Collection<String> users = invitationFacade.getAllUsers();

        ArrayList<String> listUsersWithoutInvitedUser = new ArrayList<>();
        listUsersWithoutInvitedUser.addAll(users);
        //Je m'enlève de la liste des users à inviter
        listUsersWithoutInvitedUser.remove(SessionFacade.getConnectedUser().getUserName());

        for (String u : users){
            if (invitedUsers.contains(u)){
                listUsersWithoutInvitedUser.remove(u);
            }
        }
        for (String nameUSer : listUsersWithoutInvitedUser ){
            HBoxCell hbc = new HBoxCell(nameUSer, listViewPeople);
            listPeople.add(hbc);
        }
        listViewPeople.setItems(listPeople);
    }


    public void research(ActionEvent actionEvent) {

    }
}
