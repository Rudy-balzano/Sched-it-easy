package gui.controllers;

import core.AdminAccountManagementFacade;
import core.ManagerFacade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

import java.util.Collection;

public class AccountValidationController {

    private static ManagerFacade facade = new ManagerFacade();

    @FXML
    private ListView<HBoxCell> listViewWaitingUsers;

    private class HBoxCell extends HBox {
        Label label = new Label();
        Button button = new Button("Validate account");

        HBoxCell(String labelText) {
            super();

            label.setText(labelText);
            label.setMaxWidth(Double.MAX_VALUE);
            HBox.setHgrow(label, Priority.ALWAYS);

            this.getChildren().addAll(label, button);

            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    String username = label.getText().split(" ")[0];
                    System.out.println(username);
                    facade.validateAccount(username);
                    refresh();
                }
            });
        }
    }

    private void refresh(){
        Collection<String> waitingUsers = facade.getAllWaitingUsers();

        ObservableList<HBoxCell> itemsU = FXCollections.observableArrayList();

        for (String name : waitingUsers){
            HBoxCell hbc = new HBoxCell(name);
            itemsU.add(hbc);
        }

        listViewWaitingUsers.setItems(itemsU);
    }

    @FXML
    public void initialize(){


        //Load users data from persistent layer to display it on the view
        Collection<String> waitingUsers = facade.getAllWaitingUsers();

        ObservableList<HBoxCell> itemsU = FXCollections.observableArrayList();
        for (String name : waitingUsers){
            //Consider using iterator
            HBoxCell hbc = new HBoxCell(name);
            itemsU.add(hbc);
        }
        ObservableList<AdminUsersManagementController.HBoxCell> itemsM = FXCollections.observableArrayList();

        listViewWaitingUsers.setItems(itemsU);
    }



}
