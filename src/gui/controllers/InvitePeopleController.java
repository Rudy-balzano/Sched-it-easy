package gui.controllers;

import core.InvitationFacade;
import core.SessionFacade;
import gui.Main;
import gui.roots.Roots;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class controller to invite People to a meeting
 * @version 1.0
 */
public class InvitePeopleController {

    @FXML
    private ListView<HBoxCell> listViewGroup;
    @FXML
    private ListView<HBoxCell> listViewPeople;
    @FXML
    private TextField searchBar;
    /**
     * invitationFacade
     */
    private static final InvitationFacade invitationFacade = new InvitationFacade();
    /**
     * Id of a meeting
     */
    int idMeeting = SeeInvitedPeopleController.idMeeting;

    private Collection<String> usersToInvite = new ArrayList<>();

    private Collection<String> groupsToInvite = new ArrayList<>();




    private class HBoxCell extends HBox {

        Label label = new Label();
        Button inviteButton = new Button("Invite");

        HBoxCell(String labelText, ListView listView) {

            super();

            label.setText(labelText);
            label.setMaxWidth(Double.MAX_VALUE);
            HBox.setHgrow(label, Priority.ALWAYS);

            inviteButton.setOnAction(actionEvent -> {
                if (listView.equals(listViewPeople)){
                    invitationFacade.createInvitationForUser(labelText, 0, idMeeting);
                }
                else if (listView.equals(listViewGroup)){
                    invitationFacade.createInvitationForGroup(labelText, 0, idMeeting);
                }
                refresh();
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

        System.out.println(invitedUsers);
        System.out.println(users);


        Collection<String> invitedGroups = invitationFacade.getAllInvitedGroups(idMeeting);
        Collection<String>groups = invitationFacade.getAllGroups();

        usersToInvite.addAll(users);

        usersToInvite.remove(SessionFacade.getConnectedUser().getUserName());

        groupsToInvite.addAll(groups);


        for (String u : users){
            if (invitedUsers.contains(u)){
                usersToInvite.remove(u);
            }
        }

        for (String nameUSer : usersToInvite ){
            HBoxCell hbc = new HBoxCell(nameUSer, listViewPeople);
            listPeople.add(hbc);
        }

        for (String g : groups){
            if (invitedGroups.contains(g)){
                groupsToInvite.remove(g);
            }
        }

        for (String nameGroup : groupsToInvite ){
            HBoxCell hbc = new HBoxCell(nameGroup, listViewGroup);
            listGroups.add(hbc);
        }

        listViewPeople.setItems(listPeople);
        listViewGroup.setItems(listGroups);

        searchBar.setOnKeyPressed(keyEvent -> research());


    }


    /**
     * Function used to refresh the listView
     */

    private void refresh(){

        usersToInvite = new ArrayList<>();
        groupsToInvite = new ArrayList<>();

        ObservableList<HBoxCell> listPeople =  FXCollections.observableArrayList();
        ObservableList<HBoxCell> listGroups =  FXCollections.observableArrayList();

        Collection<String> invitedUsers = invitationFacade.getAllInvitedUsers(idMeeting);
        Collection<String> users = invitationFacade.getAllUsers();


        Collection<String> invitedGroups = invitationFacade.getAllInvitedGroups(idMeeting);
        Collection<String>groups = invitationFacade.getAllGroups();

        usersToInvite.addAll(users);

        usersToInvite.remove(SessionFacade.getConnectedUser().getUserName());

        groupsToInvite.addAll(groups);


        for (String u : users){
            if (invitedUsers.contains(u)){
                usersToInvite.remove(u);
            }
        }

        for (String nameUSer : usersToInvite ){
            HBoxCell hbc = new HBoxCell(nameUSer, listViewPeople);
            listPeople.add(hbc);
        }

        for (String g : groups){
            if (invitedGroups.contains(g)){
                groupsToInvite.remove(g);
            }
        }

        for (String nameGroup : groupsToInvite ){
            HBoxCell hbc = new HBoxCell(nameGroup, listViewGroup);
            listGroups.add(hbc);
        }

        listViewPeople.setItems(listPeople);
        listViewGroup.setItems(listGroups);
    }


    @FXML
    public void research(){

        Collection<String> searchedU = new ArrayList<>();
        Collection<String> searchedG = new ArrayList<>();

        Pattern pattern = Pattern.compile(searchBar.getText());

        for (String i : usersToInvite){
            Matcher matcher = pattern.matcher(i);
            if(matcher.find()){
                searchedU.add(i);
            }
        }
        for (String i : groupsToInvite){
            Matcher matcher = pattern.matcher(i);
            if(matcher.find()){
                searchedG.add(i);
            }
        }

        ObservableList<HBoxCell> listPeople =  FXCollections.observableArrayList();
        ObservableList<HBoxCell> listGroups =  FXCollections.observableArrayList();

        for (String nameUSer : searchedU ){
            HBoxCell hbc = new HBoxCell(nameUSer, listViewPeople);
            listPeople.add(hbc);
        }

        for (String nameGroup : searchedG ){
            HBoxCell hbc = new HBoxCell(nameGroup, listViewGroup);
            listGroups.add(hbc);
        }

        listViewPeople.setItems(listPeople);
        listViewGroup.setItems(listGroups);
    }

    public void handleCancel() throws IOException {
        if(SessionFacade.getConnectedUser().getIsManager()) {
            Main.scheditWindow.setScene(new Scene(FXMLLoader.load(getClass().getResource(Roots.managerHomeRoot))));
        } else {
            Main.scheditWindow.setScene(new Scene(FXMLLoader.load(getClass().getResource(Roots.userHomeRoot))));
        }
    }

}
