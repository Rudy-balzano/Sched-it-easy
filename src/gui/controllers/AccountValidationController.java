package gui.controllers;

import core.AdminAccountManagementFacade;
import core.ManagerFacade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

import java.util.Collection;

public class AccountValidationController {

    @FXML
    private ListView<HBoxCell> listViewU;

    private static class HBoxCell extends HBox {
        Label label = new Label();
        Button button = new Button("Validate account");

        HBoxCell(String labelText) {
            super();

            label.setText(labelText);
            label.setMaxWidth(Double.MAX_VALUE);
            HBox.setHgrow(label, Priority.ALWAYS);

            this.getChildren().addAll(label, button);
        }
    }

    @FXML
    public void initialize(){

        ManagerFacade facade = new ManagerFacade();

        //Load users data from persistent layer to display it on the view
        Collection<String> waitingUsers = facade.getAllWaitingUsers();

        ObservableList<HBoxCell> itemsU = FXCollections.observableArrayList();
        for (String name : waitingUsers){
            //Consider using iterator
            HBoxCell hbc = new HBoxCell(name);
            itemsU.add(hbc);
        }
        ObservableList<AdminUsersManagementController.HBoxCell> itemsM = FXCollections.observableArrayList();

        listViewU.setItems(itemsU);
    }



}
