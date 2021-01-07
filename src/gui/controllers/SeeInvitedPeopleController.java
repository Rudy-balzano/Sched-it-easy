package gui.controllers;

import core.Group;
import core.InvitationFacade;
import core.ManagerFacade;
import core.SessionFacade;
import gui.Main;
import gui.roots.Roots;
import gui.views.popover.MyCustomPopOverHomeView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

import java.io.IOException;
import java.util.Collection;

public class SeeInvitedPeopleController {

    private static final InvitationFacade invitationFacade = new InvitationFacade();

    public static int idMeeting = MyCustomPopOverHomeView.idMeeting;


    @FXML
    private ListView<HBoxCell> listViewInvitedUsers;

    @FXML
    private ListView<HBoxCell> listViewInvitedGroups;


    private class HBoxCell extends HBox {
        Label label = new Label();

        HBoxCell(String labelText) {
            super();

            label.setText(labelText);
            label.setMaxWidth(Double.MAX_VALUE);
            HBox.setHgrow(label, Priority.ALWAYS);

            this.getChildren().addAll(label);

        }
    }

    @FXML
    public void initialize(){
        //TODO integrer l'id meeting

        Collection<String> invitedUsers = invitationFacade.getAllInvitedUsers(idMeeting);
        Collection<String> invitedGroups = invitationFacade.getAllInvitedGroups(idMeeting);

        ObservableList<HBoxCell> itemsG = FXCollections.observableArrayList();
        ObservableList<HBoxCell> itemsU = FXCollections.observableArrayList();

        for (String nameUser : invitedUsers){
            HBoxCell hbc = new HBoxCell(nameUser);
            itemsU.add(hbc);
        }

        for (String nameGroup : invitedGroups){
            HBoxCell hbc = new HBoxCell(nameGroup);
            itemsG.add(hbc);
        }


        listViewInvitedUsers.setItems(itemsU);
        listViewInvitedGroups.setItems(itemsG);

    }


    public void handleInviteUser() throws IOException {
        Main.scheditWindow.setScene(new Scene(FXMLLoader.load(getClass().getResource(Roots.invitePeopleRoot))));
    }

    public void handleGoBack() throws IOException {
        if(SessionFacade.getConnectedUser().getIsManager()){
            Main.scheditWindow.setScene(new Scene(FXMLLoader.load(getClass().getResource(Roots.managerHomeRoot))));
        }
        else{
            Main.scheditWindow.setScene(new Scene(FXMLLoader.load(getClass().getResource(Roots.userHomeRoot))));
        }
    }
}
