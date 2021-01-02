package gui.controllers;

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

    private static final ManagerFacade facade = new ManagerFacade();

    @FXML
    private ListView<HBoxCell> listViewWaitingUsers;

    private class HBoxCell extends HBox {
        Label label = new Label();
        Button button = new Button("Validate account");
        Button button2 = new Button("Decline account");

        HBoxCell(String labelText) {
            super();

            label.setText(labelText);
            label.setMaxWidth(Double.MAX_VALUE);
            HBox.setHgrow(label, Priority.ALWAYS);

            this.getChildren().addAll(label, button);

            button.setOnAction(actionEvent -> {
                String username = label.getText().split(" ")[0];
                facade.validateAccount(username);
                refresh();
            });
            button2.setOnAction(actionEvent -> {
                String username = label.getText().split(" ")[0];
                facade.declineAccount(username);
                refresh();
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

        Collection<String> waitingUsers = facade.getAllWaitingUsers();

        ObservableList<HBoxCell> itemsU = FXCollections.observableArrayList();
        for (String name : waitingUsers){
            HBoxCell hbc = new HBoxCell(name);
            itemsU.add(hbc);
        }

        listViewWaitingUsers.setItems(itemsU);
    }



}
