package gui.controllers;

import core.InvitationFacade;
import core.ManagerFacade;
import core.SessionFacade;
import gui.Main;
import gui.roots.Roots;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

public class InvitePeopleController {

    private static final InvitationFacade facade = new InvitationFacade();

    @FXML
    private ListView<HBoxCell> listViewInvitedUsers;

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
        int idMeeting = 1;
        Collection<String> invitedUsers = facade.getAllInvitedUsers(idMeeting);

        ObservableList<HBoxCell> itemsU = FXCollections.observableArrayList();
        for (String name : invitedUsers){
            HBoxCell hbc = new HBoxCell(name);
            itemsU.add(hbc);
        }

        listViewInvitedUsers.setItems(itemsU);
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
